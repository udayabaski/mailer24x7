package com.nervytech.mailer24x7.campaign.job.task;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.RejectedExecutionException;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.nervytech.mailer24x7.client.exception.MailerException;
import com.nervytech.mailer24x7.common.enums.CampaignStatusEnum;
import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.domain.model.Campaign;
import com.nervytech.mailer24x7.mailgun.MailgunCampignSyncer;
import com.nervytech.mailer24x7.mailgun.MailgunSendUtil;
import com.nervytech.mailer24x7.model.service.api.ICampaignStatusService;
import com.sun.jersey.api.client.ClientResponse;

public class CampaignTaskExecutor {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignTaskExecutor.class);

	@Autowired
	private ICampaignStatusService cmpnStatusService;

	@Autowired
	private MailgunCampignSyncer mailgunSyncer;

	//private ThreadPoolTaskExecutor taskExecutor;

	public void addCampaignSenderTask(final Campaign cmpn,ThreadPoolTaskExecutor taskExecutor) {

		try {

			taskExecutor.execute(new Thread(new Runnable() {

				@Override
				public void run() {
					try {

						String content = null;
						try {
							content = FileUtils.readFileToString(new File(cmpn
									.getImageLocation()));

						} catch (IOException e) {

							logger.error("Unable to read the file ", e);

							e.printStackTrace();

							throw new MailerException(
									"Exception occured while reading the editor content.",
									e);

						}

						System.out
								.println("Updating Campaign in Mailgun for Campign ID : "
										+ cmpn.getCampaignId());

						mailgunSyncer.updateCampaignInMailgun(cmpn);

						ClientResponse response = MailgunSendUtil.sendMail(
								cmpn.getCampaignId(), cmpn.getSenderEmailId(),
								cmpn.getSubscriberListId(), cmpn.getOrgId(),
								cmpn.getSubject(), null, content,null, null);

						System.out.println("Mail Sent Status is : "
								+ response.getClientResponseStatus().toString()
								+ " Status ===>> " + response.getStatus());

						cmpnStatusService.updateCampaignStatus(
								cmpn.getCampaignId(),
								CampaignStatusEnum.COMPLETED.getStatus(),
								MailerUtil.FORMATTER_WITH_TIME.format(new Date()));

						System.out.println("Updated Campaign Status : "
								+ cmpn.getCampaignId());

					} catch (Exception e) {
						logger.error(
								"Error sending campaign to :"
										+ cmpn.getCampaignId() + " ", e);
						e.printStackTrace();
					}

				}
			}));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCampaignSyncherTask(final Campaign cmpn,ThreadPoolTaskExecutor taskExecutor) {

		try {
			taskExecutor.execute(new Thread(new Runnable() {

				@Override
				public void run() {

					System.out.println("Update Campaign Stats Started : "
							+ cmpn.getCampaignId());

					mailgunSyncer.updateCampainStats(cmpn.getCampaignId(),
							cmpn.getCampaignName(), null);

					try {
						System.out.println("Updating Event : "
								+ cmpn.getCampaignId());

						mailgunSyncer.updateEventsFromMailgun(cmpn
								.getCampaignId());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}));
		} catch (RejectedExecutionException e) {
			// will be thrown if rejected execution handler
			// is not set with executor.setRejectedExecutionHandler
			e.printStackTrace();
		}

	}

}
