/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import com.nervytech.mailer24x7.common.enums.MailgunSyncStatusEnum;
import com.nervytech.mailer24x7.domain.model.CampaignStatus;
import com.nervytech.mailer24x7.mailgun.CampaignStatsVO;

/**
 * @author bsikkaya
 * 
 */
public interface ICampaignStatusService {
	
	public void updateCampaignStatus(long campaignId, int status,
			String sentTime);

	public void updateCampaignSubscriberStatus(CampaignStatsVO cmpnStatusVO);

	public void updateCampaignEventsStats(CampaignStatsVO cmpnStatusVO);

	public void updateCampaignSyncStatus(long campaignId,
			MailgunSyncStatusEnum status);

	public void updateDeliveredCount(long campaignId, int deliveredCount);
	
	public int getCampaignEventFetchCount(long campaignId);
	
	public void updateLatestCampaignSubscriberId(long campaignId,
			long subscriberId);

	public long getLatestCampaignSubscriberId(long campaignId);

	public void updateCampaignEventFetchCount(long campaignId, int count);
	
	public void updateCampaignStatus(CampaignStatsVO cmpnStatusVO);
	
	public void saveCampaignStatus(CampaignStatus cmpnStatus);

	public void updateCampaignStatusSender(CampaignStatus cmpnStatus);

	public void updateS3Path(String s3Path, long campaignId, String time);
	
	public void updateSubscriberListId(long campaignId, String subscriberGroup);

	public String getS3Path(String campaignId);

}
