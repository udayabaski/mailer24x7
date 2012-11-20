/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nervytech.mailer24x7.campaign.job.scheduler.BiHourlyCampaignStatsSyncJob;
import com.nervytech.mailer24x7.domain.model.Campaign;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignDAO;
import com.nervytech.mailer24x7.model.service.api.ICampaignService;

/**
 * @author bsikkaya
 *
 */
public class CampaignService implements ICampaignService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(CampaignService.class);
	
	@Autowired
	private ICampaignDAO campaignDAO;
	
	public List<Campaign> getCampaignsToSyncBiHoursStats(int status,
			String time, long lowerLimit, long upperLimit){
		List<Campaign> cmpnList = null;
		try {
			cmpnList = campaignDAO
					.getCampaignsToSyncBiHoursStats(status,time,lowerLimit,upperLimit);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return cmpnList;
	}
}
