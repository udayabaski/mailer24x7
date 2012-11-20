/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import java.util.List;

import com.nervytech.mailer24x7.mailgun.CampaignEvent;

/**
 * @author bsikkaya
 *
 */
public interface ISubscriberReportsDAO {
	
	public void addCampaignEvents(final long campaignId,
			final List<CampaignEvent> eventList);
	
}
