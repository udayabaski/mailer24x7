/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberListDAO;
import com.nervytech.mailer24x7.model.service.api.ISubscriberListService;

/**
 * @author bsikkaya
 *
 */
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

}
