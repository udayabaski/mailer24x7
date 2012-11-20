/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nervytech.mailer24x7.mailgun.CampaignEvent;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberReportsDAO;
import com.nervytech.mailer24x7.model.service.api.ISubscriberReportsService;

/**
 * @author bsikkaya
 *
 */
public class SubscriberReportsService implements ISubscriberReportsService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(SubscriberReportsService.class);
	
	@Autowired
	private ISubscriberReportsDAO subscriberReportsDAO;

	@Override
	public void addCampaignEvents(long campaignId, List<CampaignEvent> eventList) {
		// TODO Auto-generated method stub
		try {
			subscriberReportsDAO.addCampaignEvents(campaignId, eventList);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	
	
}