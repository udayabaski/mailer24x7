/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import java.util.List;

import com.nervytech.mailer24x7.domain.model.Campaign;
import com.nervytech.mailer24x7.domain.model.CampaignSchedulerModel;
import com.nervytech.mailer24x7.spring.bean.CampaignBean;
import com.nervytech.mailer24x7.spring.bean.CampaignReportsBean;
import com.nervytech.mailer24x7.spring.bean.CampaignSnapshotBean;

/**
 * @author bsikkaya
 * 
 */
public interface ICampaignService {

	public List<Campaign> getCampaignsToSyncBiHoursStats(int status,
			String time, long lowerLimit, long upperLimit);

	public List<CampaignBean> getLatestCampaigns(int count, long orgId);

	public List<CampaignBean> getCampaigns(long orgId, int status);

	public void deleteCampaign(long campaignId);

	public long getOrgId(long campaignId);

	public CampaignSnapshotBean getCampaign(long campaignId);

	public long saveCampaign(Campaign cmpn);

	public void updateCampaign(Campaign cmpn);

	public void updateContentType(long campaignId, int contentType);

	public List<CampaignSchedulerModel> getScheduledCampaigns(int status,
			String toTime, int rowCounts);

	public List<CampaignSchedulerModel> getScheduledCampaigns(int status,
			int rowCounts);
	
	public List<Campaign> getCampaignsToSyncTwelveHoursStats(int status,
			String time, long lowerLimit, long upperLimit);

	public int getCampaignType(long campaignId);

	public CampaignReportsBean getCampaignBean(String campaignId);

}
