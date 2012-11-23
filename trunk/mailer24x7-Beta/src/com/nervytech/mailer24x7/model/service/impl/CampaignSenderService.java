/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nervytech.mailer24x7.domain.model.CampaignSender;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignSenderDAO;
import com.nervytech.mailer24x7.model.service.api.ICampaignSenderService;

/**
 * @author bsikkaya
 *
 */
public class CampaignSenderService implements ICampaignSenderService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CampaignSenderService.class);
	
	@Autowired
	private ICampaignSenderDAO campaignSenderDAO;
	
	@Override
	public long getCampaignSenderId(CampaignSender cmpnSender) {
		long senderId = -1;
		try {
			senderId = campaignSenderDAO.getCampaignSenderId(cmpnSender);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return senderId;
	}

	@Override
	public long saveCampaignSender(CampaignSender cmpnSender) {
		long senderId = -1;
		try {
			senderId = campaignSenderDAO.saveCampaignSender(cmpnSender);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return senderId;
	}

}
