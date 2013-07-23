/**
 * 
 */
package com.nervytech.mailer24x7.reports.bean;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nervytech.mailer24x7.domain.model.SubscriberReports;

/**
 * @author ADMIN
 * 
 */
@Repository("subscriberStatusReportsBean")
public class SubscriberStatusReportsBean {

	private List<SubscriberReports> subscriberList;

	/**
	 * @return the subscriberList
	 */
	public List<SubscriberReports> getSubscriberList() {
		return subscriberList;
	}

	/**
	 * @param subscriberList
	 *            the subscriberList to set
	 */
	public void setSubscriberList(List<SubscriberReports> subscriberList) {
		this.subscriberList = subscriberList;
	}

}
