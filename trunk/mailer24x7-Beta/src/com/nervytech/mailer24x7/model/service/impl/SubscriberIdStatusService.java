/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberIdStatusDAO;
import com.nervytech.mailer24x7.model.service.api.ISubscriberIdStatusService;

/**
 * @author bsikkaya
 * 
 */
@Service
public class SubscriberIdStatusService implements ISubscriberIdStatusService {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignStatusService.class);

	@Autowired
	private ISubscriberIdStatusDAO subscriberIdStatusDAO;

	@Override
	@Transactional
	public List<SubscriberIdStatus> getNextSubscribers(Long subListId,
			Long lastSubcriberId, int status, int limit) {
		List<SubscriberIdStatus> list = null;
		try {
			list = subscriberIdStatusDAO.getNextSubscribers(subListId,
					lastSubcriberId, status, limit);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	@Transactional
	public List<SubscriberIdStatus> getSubscribersByLatestCount(
			String subListId, int status, long latestSubscriberSent) {
		List<SubscriberIdStatus> list = null;
		try {
			list = subscriberIdStatusDAO.getSubscribersByLatestCount(subListId,
					status, latestSubscriberSent);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	public void updateSubscriberStatus(long subListId,
			List<String> emailIdList, int status) {
		try {
			subscriberIdStatusDAO.updateSubscriberStatus(subListId,
					emailIdList, status);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public int getSubscribersCount(String subscriberListId) {
		int count = 0;
		try {
			count = subscriberIdStatusDAO.getSubscribersCount(subscriberListId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<List<SubscriberIdStatus>> getAllSubscribers(long subListId) {
		List<List<SubscriberIdStatus>> list = null;
		try {
			list = subscriberIdStatusDAO.getAllSubscribers(subListId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	public void addBatchSubscriber(long subscriberListId, long userId,
			long orgId, int status, String[] subscribers) {
		try {
			subscriberIdStatusDAO.addBatchSubscriber(subscriberListId, userId,
					orgId, status, subscribers);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteSubGroup(long subListId) {
		try {
			subscriberIdStatusDAO.deleteSubGroup(subListId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteSubscriber(String statusIds) {
		try {
			subscriberIdStatusDAO.deleteSubscriber(statusIds);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateSubscribersStatus(String statusIds, int status) {
		try {
			subscriberIdStatusDAO.updateSubscribersStatus(statusIds, status);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public List<Long> getAllSubscribersByStatus(long subscriberListId,
			int status) {
		List<Long> list = null;
		try {
			list = subscriberIdStatusDAO.getAllSubscribersByStatus(subscriberListId,status);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	public void addCampaignSentForSubscribers(List<Long> subscriebrs,
			long campaignId) {
		try {
			subscriberIdStatusDAO.addCampaignSentForSubscribers(subscriebrs, campaignId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		
	}
}
