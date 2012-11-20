/**
 * 
 */
package com.nervytech.mailer24x7.campaign.job.scheduler;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.nervytech.mailer24x7.campaign.job.task.CampaignTaskExecutor;
import com.nervytech.mailer24x7.common.enums.CampaignStatusEnum;
import com.nervytech.mailer24x7.common.util.MailerUtil;
import com.nervytech.mailer24x7.model.dao.CampaignDAO;
import com.nervytech.mailer24x7.model.domains.Campaign;

/**
 * @author bsikkaya
 * 
 */
public class TwelveHoursCampaignStatsSyncJob {

	private static final Logger logger = LoggerFactory
			.getLogger(BiHourlyCampaignStatsSyncJob.class);

	private ThreadPoolTaskExecutor taskExecutor;

	public TwelveHoursCampaignStatsSyncJob(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	@Autowired
	private CampaignDAO campaignDAO;

	@Autowired
	private CampaignTaskExecutor cmpnTaskExecutor;

	public void run() {


		logger.info("Campaign Stats Sync Job started !!!!");

		try {
			
			//cmpnTaskExecutor.setTaskExecutor(taskExecutor);
			
			long time = System.currentTimeMillis();
			time = time - (2l * 24l * 60l * 60l * 1000);

			long lowerLimit = 0l;
			long upperLimit = 20000l;
			long batchCount = 20000l;

			List<Campaign> campaignsList = campaignDAO
					.getCampaignsToSyncTwelveHoursStats(
							CampaignStatusEnum.COMPLETED.getStatus(),
							MailerUtil.formatter.format(new Date(time)),
							lowerLimit, upperLimit);

			while (campaignsList != null && campaignsList.size() > 0) {

				for (Campaign cmpn : campaignsList) {

					logger.info("Sending campaign started for the campaignId : "
							+ cmpn.getCampaignId());

					cmpnTaskExecutor.addCampaignSyncherTask(cmpn,taskExecutor);
					
					logger.info("Successfuly Synced Campaign satts for the campaignId : "
							+ cmpn.getCampaignId());
				}

				while (taskExecutor.getActiveCount() > 0) {
					System.out.println("Active count is "
							+ taskExecutor.getActiveCount());
					Thread.sleep(2000);
				}

				if (campaignsList.size() >= batchCount) {
					lowerLimit = lowerLimit + batchCount;
					upperLimit = upperLimit + batchCount;

					campaignsList = campaignDAO.getCampaignsToSyncBiHoursStats(
							CampaignStatusEnum.COMPLETED.getStatus(),
							MailerUtil.formatter.format(new Date(time)),
							lowerLimit, upperLimit);
				} else {
					campaignsList = null;
				}

			}

			logger.info("CampaignNow Job Finished !!!!");

		} catch (Exception e) {
			logger.error("Error execeuting campaign Sync job ", e);
		}
	}

}