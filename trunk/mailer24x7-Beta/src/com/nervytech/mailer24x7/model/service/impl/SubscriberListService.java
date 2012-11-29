/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nervytech.mailer24x7.domain.model.SubscriberList;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberListDAO;
import com.nervytech.mailer24x7.model.service.api.ISubscriberListService;

/**
 * @author bsikkaya
 *
 */
@Service
public class SubscriberListService implements ISubscriberListService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(CampaignStatusService.class);
	
	@Autowired
	private ISubscriberListDAO subscriberListDAO;
	
	public void updateBounceCounts(final long subListId,
			List<String> emailIdList) {
		try {
			subscriberListDAO.updateBounceCounts(subListId, emailIdList);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateUnSubscriberCounts(long subListId,
			List<String> emailIdList) {
		try {
			subscriberListDAO.updateUnSubscriberCounts(subListId, emailIdList);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}

	@Override
	public List<SubscriberList> getSubscriberGroups(String orgId) {
		List<SubscriberList> subscribersList = null;
		try {
			subscribersList = subscriberListDAO.getSubscriberGroups(orgId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return subscribersList;
	}

	@Override
	public long getSubscriberGroupByListName(String listName, long orgId) {
		long sublistId = 0;
		try {
			sublistId = subscriberListDAO.getSubscriberGroupByListName(listName,orgId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return sublistId;
	}

	@Override
	public long addSubGroup(SubscriberList subList) {
		long sublistId = 0;
		try {
			sublistId = subscriberListDAO.addSubGroup(subList);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return sublistId;
	}

	@Override
	public List<SubscriberList> getSubscriberGroup(long subscriberListId) {
		List<SubscriberList> list = null;
		try {
			list = subscriberListDAO.getSubscriberGroup(subscriberListId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	public void moveSubscribers(long subscriberListId, int length,
			int subscriberType, int moveTo) {
		try {
			subscriberListDAO.moveSubscribers(subscriberListId,length,subscriberType,moveTo);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateUnsubscriberCount(long subscriberListId, int length) {
		try {
			subscriberListDAO.updateUnsubscriberCount(subscriberListId,length);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}

	@Override
	public void updateBouncedCount(long subscriberListId, int length) {
		try {
			subscriberListDAO.updateBouncedCount(subscriberListId,length);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}

	@Override
	public void updateActiveCount(long subscriberListId, int length) {
		try {
			subscriberListDAO.updateActiveCount(subscriberListId,length);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}
	
	@Override
	public void addActiveCount(long subscriberListId, int length) {
		try {
			subscriberListDAO.addActiveCount(subscriberListId,length);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}
}
