/**
 * 
 */
package com.nervytech.mailer24x7.campaign.job.scheduler;

import java.util.Calendar;
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
public class CampaignLaterJob {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignLaterJob.class);

	private ThreadPoolTaskExecutor taskExecutor;

	public CampaignLaterJob(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	@Autowired
	private CampaignDAO campaignDAO;
	
	@Autowired
	private CampaignTaskExecutor cmpnTaskExecutor;

	public void run() {

		try {
			
			//cmpnTaskExecutor.setTaskExecutor(taskExecutor);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MINUTE, 30);

			Date toTimeDt = cal.getTime();
			String toTime = MailerUtil.formatter.format(toTimeDt);

			List<Campaign> campaignsList = campaignDAO.getScheduledCampaigns(
					CampaignStatusEnum.SCHEDULED.getStatus(), toTime,
					MailerUtil.ROW_FETCH_SIZE);

			for (Campaign cmpn : campaignsList) {

				logger.info("Sending campaign started for the campaignId : "
						+ cmpn.getCampaignId());

				System.out.println("Sending Camaign ID : "
						+ cmpn.getCampaignId() + " Satus is : "
						+ cmpn.getStatus());

				
				cmpnTaskExecutor.addCampaignSenderTask(cmpn,taskExecutor);
				
				logger.info("Campaign sent successfully for the campaignId : "
						+ cmpn.getCampaignId());

			}

			while (taskExecutor.getActiveCount() > 0) {
				System.out.println("Active count is "
						+ taskExecutor.getActiveCount());
				Thread.sleep(2000);
			}

			logger.info("CampaignNow Job Finished !!!!");

		} catch (Exception e) {
			logger.error("Error execeuting campaign Later job ", e);

			e.printStackTrace();
		}
	}

}