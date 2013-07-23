/**
 * 
 */
package com.nervytech.mailer24x7.reports.bean;

import org.springframework.stereotype.Repository;

/**
 * @author ADMIN
 * 
 */
@Repository("subscriberCampaignBean")
public class SubscriberCampaignBean {

	private long campaignId;
	private String campaignName;
	private String campaignDate;
	private int campaignOpensCount;

	/**
	 * @return the campaignId
	 */
	public long getCampaignId() {
		return campaignId;
	}

	/**
	 * @param campaignId
	 *            the campaignId to set
	 */
	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * @return the campaignName
	 */
	public String getCampaignName() {
		return campaignName;
	}

	/**
	 * @param campaignName
	 *            the campaignName to set
	 */
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	/**
	 * @return the campaignDate
	 */
	public String getCampaignDate() {
		return campaignDate;
	}

	/**
	 * @param campaignDate
	 *            the campaignDate to set
	 */
	public void setCampaignDate(String campaignDate) {
		this.campaignDate = campaignDate;
	}

	/**
	 * @return the campaignOpensCount
	 */
	public int getCampaignOpensCount() {
		return campaignOpensCount;
	}

	/**
	 * @param campaignOpensCount
	 *            the campaignOpensCount to set
	 */
	public void setCampaignOpensCount(int campaignOpensCount) {
		this.campaignOpensCount = campaignOpensCount;
	}

}