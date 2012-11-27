/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nervytech.mailer24x7.domain.model.Campaign;
import com.nervytech.mailer24x7.domain.model.CampaignSchedulerModel;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignDAO;
import com.nervytech.mailer24x7.model.service.api.ICampaignService;
import com.nervytech.mailer24x7.spring.bean.CampaignBean;
import com.nervytech.mailer24x7.spring.bean.CampaignSnapshotBean;

/**
 * @author bsikkaya
 * 
 */
@Service
public class CampaignService implements ICampaignService {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignService.class);

	@Autowired
	private ICampaignDAO campaignDAO;

	public List<Campaign> getCampaignsToSyncBiHoursStats(int status,
			String time, long lowerLimit, long upperLimit) {
		List<Campaign> cmpnList = null;
		try {
			cmpnList = campaignDAO.getCampaignsToSyncBiHoursStats(status, time,
					lowerLimit, upperLimit);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return cmpnList;
	}

	public List<CampaignBean> getLatestCampaigns(int count, long orgId) {
		List<CampaignBean> cmpnList = null;
		try {
			cmpnList = campaignDAO.getLatestCampaigns(count, orgId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return cmpnList;
	}

	public List<CampaignBean> getCampaigns(long orgId, int status) {
		List<CampaignBean> cmpnList = null;
		try {
			cmpnList = campaignDAO.getCampaigns(orgId, status);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return cmpnList;
	}

	public void deleteCampaign(long campaignId) {
		try {
			campaignDAO.deleteCampaign(campaignId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	public long getOrgId(long campaignId) {
		long orgId = -1;
		try {
			orgId = campaignDAO.getOrgId(campaignId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return orgId;
	}

	@Override
	public CampaignSnapshotBean getCampaign(long campaignId) {
		CampaignSnapshotBean cmpnBean = null;
		try {
			List<CampaignSnapshotBean> cmpnBeansList = campaignDAO
					.getCampaign(campaignId);
			if (cmpnBeansList != null && cmpnBeansList.size() > 0) {
				cmpnBean = cmpnBeansList.get(0);
			}
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return cmpnBean;
	}

	@Override
	public long saveCampaign(Campaign cmpn) {
		long campaignId = -1;
		try {
			campaignId = campaignDAO.saveCampaign(cmpn);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return campaignId;
	}

	@Override
	public void updateCampaign(Campaign cmpn) {
		try {
			campaignDAO.updateCampaign(cmpn);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateContentType(long campaignId, int campaignType) {
		try {
			campaignDAO.updateContentType(campaignId, campaignType);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<CampaignSchedulerModel> getScheduledCampaigns(int status,
			String toTime, int rowCounts) {
		List<CampaignSchedulerModel> cmpnBeansList = null;
		try {
			cmpnBeansList = campaignDAO.getScheduledCampaigns(status, toTime,
					rowCounts);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return cmpnBeansList;
	}

	@Override
	public List<CampaignSchedulerModel> getScheduledCampaigns(int status,
			int rowCounts) {
		List<CampaignSchedulerModel> cmpnBeansList = null;
		try {
			cmpnBeansList = campaignDAO
					.getScheduledCampaigns(status, rowCounts);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return cmpnBeansList;
	}

	@Override
	public List<Campaign> getCampaignsToSyncTwelveHoursStats(int status,
			String time, long lowerLimit, long upperLimit) {
		List<Campaign> cmpnBeansList = null;
		try {
			cmpnBeansList = campaignDAO.getCampaignsToSyncTwelveHoursStats(
					status, time, lowerLimit, upperLimit);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return cmpnBeansList;
	}

	@Override
	public int getCampaignType(long campaignId) {
		int campaignType = -1;
		try {
			campaignType = campaignDAO.getCampaignType(campaignId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return campaignType;
	}

}
