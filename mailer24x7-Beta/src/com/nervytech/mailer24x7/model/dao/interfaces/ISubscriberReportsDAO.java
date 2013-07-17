/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.nervytech.mailer24x7.mailgun.CampaignEvent;

/**
 * @author bsikkaya
 * 
 */
public interface ISubscriberReportsDAO {

	public void addCampaignEvents(final long campaignId,
			final List<CampaignEvent> eventList);

	public Map<Integer, List<Long>> getSubscriberReport(long campaignId,
			int openSatus, int clickStatus);

	public Map<Integer, Map<String, Integer>> getSubscriberRegionReport(
			long campaignIdLong);

}
