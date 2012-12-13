/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nervytech.mailer24x7.common.enums.MailgunSyncStatusEnum;
import com.nervytech.mailer24x7.domain.model.CampaignStatus;
import com.nervytech.mailer24x7.mailgun.CampaignStatsVO;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignStatusDAO;
import com.nervytech.mailer24x7.model.service.api.ICampaignStatusService;

/**
 * @author bsikkaya
 * 
 */
@Service
public class CampaignStatusService implements ICampaignStatusService {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignStatusService.class);

	@Autowired
	private ICampaignStatusDAO cmpnStatusDAO;

	@Transactional
	public void updateCampaignSyncStatus(long campaignId,
			MailgunSyncStatusEnum status) {
		try {
			cmpnStatusDAO.updateCampaignSyncStatus(campaignId, status);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Transactional
	public void updateCampaignSubscriberStatus(CampaignStatsVO cmpnStatusVO) {
		try {
			cmpnStatusDAO.updateCampaignSubscriberStatus(cmpnStatusVO);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void updateCampaignEventsStats(CampaignStatsVO cmpnStatusVO) {
		try {
			cmpnStatusDAO.updateCampaignEventsStats(cmpnStatusVO);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	@Transactional
	public void updateDeliveredCount(long campaignId, int deliveredCount) {
		try {
			cmpnStatusDAO.updateDeliveredCount(campaignId, deliveredCount);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	@Transactional
	public int getCampaignEventFetchCount(long campaignId) {
		int fetchCount = -1;
		try {
			fetchCount = cmpnStatusDAO.getCampaignEventFetchCount(campaignId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return fetchCount;
	}

	@Override
	public void updateLatestCampaignSubscriberId(long campaignId,
			long subscriberId) {
		try {
			cmpnStatusDAO.updateLatestCampaignSubscriberId(campaignId,
					subscriberId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public long getLatestCampaignSubscriberId(long campaignId) {
		long subscriberId = -1;
		try {
			subscriberId = cmpnStatusDAO
					.getLatestCampaignSubscriberId(campaignId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return subscriberId;
	}

	@Override
	public void updateCampaignEventFetchCount(long campaignId, int count) {
		try {
			cmpnStatusDAO.updateCampaignEventFetchCount(campaignId, count);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateCampaignStatus(CampaignStatsVO cmpnStatusVO) {
		try {
			cmpnStatusDAO.updateCampaignStatus(cmpnStatusVO);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}

	@Override
	public void updateCampaignStatus(long campaignId, int status,
			String sentTime) {
		try {
			cmpnStatusDAO.updateCampaignStatus(campaignId,status,sentTime);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
		
	}

	@Override
	public void saveCampaignStatus(CampaignStatus cmpnStatus) {
		try {
			cmpnStatusDAO.saveCampaignStatus(cmpnStatus);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}

	@Override
	public void updateCampaignStatusSender(CampaignStatus cmpnStatus) {
		try {
			cmpnStatusDAO.updateCampaignStatusSender(cmpnStatus);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateS3Path(String s3Path, long campaignId, String time) {
		try {
			cmpnStatusDAO.updateS3Path(s3Path,campaignId,time);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}
	
	@Override
	public void updateSubscriberListId(long campaignId, String subscriberGroup) {
		try {
			cmpnStatusDAO.updateSubscriberListId(campaignId, subscriberGroup);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}

	@Override
	public String getS3Path(String campaignId) {
		String s3Path = null;
		try {
			s3Path = cmpnStatusDAO
					.getS3Path(campaignId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return s3Path;
	}

	@Override
	public void scheduleCampaignNow(String campaignId, int status,String confirmationMail, String updateTime) {
		try {
			cmpnStatusDAO.scheduleCampaignNow(campaignId, status, confirmationMail, updateTime);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}	
	}

	@Override
	public void scheduleCampaignLater(String campaignId, int status,
			String confirmationMailIdLater, String scheduledTime,
			String timezone, String updatedTime) {
		try {
			cmpnStatusDAO.scheduleCampaignLater(campaignId, status, confirmationMailIdLater, scheduledTime, timezone, updatedTime);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}
	
}
