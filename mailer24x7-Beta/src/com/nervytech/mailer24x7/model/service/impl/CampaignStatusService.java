/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nervytech.mailer24x7.common.enums.MailgunSyncStatusEnum;
import com.nervytech.mailer24x7.mailgun.CampaignStatsVO;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignStatusDAO;
import com.nervytech.mailer24x7.model.service.api.ICampaignStatusService;

/**
 * @author bsikkaya
 * 
 */
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
}