/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import java.util.List;

import com.nervytech.mailer24x7.mailgun.CampaignEvent;

/**
 * @author bsikkaya
 *
 */
public interface ISubscriberReportsService {
	
	public void addCampaignEvents(final long campaignId,
			final List<CampaignEvent> eventList);

}
