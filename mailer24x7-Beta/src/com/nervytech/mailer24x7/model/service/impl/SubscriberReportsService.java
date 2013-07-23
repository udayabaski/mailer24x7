/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nervytech.mailer24x7.mailgun.CampaignEvent;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberReportsDAO;
import com.nervytech.mailer24x7.model.service.api.ISubscriberReportsService;
import com.nervytech.mailer24x7.reports.bean.BarChartReportsBean;

/**
 * @author bsikkaya
 * 
 */
@Service
public class SubscriberReportsService implements ISubscriberReportsService {

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

	@Override
	public Map<Integer, List<Long>> getSubscriberReport(long campaignId,
			int openSatus, int clickStatus) {
		Map<Integer, List<Long>> map = null;
		try {
			map = subscriberReportsDAO.getSubscriberReport(campaignId,
					openSatus, clickStatus);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, BarChartReportsBean> getSubscriberRegionReport(
			long campaignIdLong, int opened, int clicked, int bounced, int unsubscribed) {
		Map<String, BarChartReportsBean> map = null;
		try {
			map = subscriberReportsDAO.getSubscriberRegionReport(
					campaignIdLong,opened,clicked, bounced, unsubscribed);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return map;
	}

}
