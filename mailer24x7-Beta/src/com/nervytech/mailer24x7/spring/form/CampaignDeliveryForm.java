/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;

/**
 * @author ADMIN
 * 
 */
@Repository("campaignDeliveryForm")
public class CampaignDeliveryForm {

	private String campaignId;
	private String confirmationMailId;
	private String nextAction;

	/**
	 * @return the testMailId
	 */
	public String getTestMailId() {
		return testMailId;
	}

	/**
	 * @param testMailId
	 *            the testMailId to set
	 */
	public void setTestMailId(String testMailId) {
		this.testMailId = testMailId;
	}

	/**
	 * @return the campaignId
	 */
	public String getCampaignId() {
		return campaignId;
	}

	/**
	 * @param campaignId
	 *            the campaignId to set
	 */
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * @return the nextAction
	 */
	public String getNextAction() {
		return nextAction;
	}

	/**
	 * @param nextAction
	 *            the nextAction to set
	 */
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

}