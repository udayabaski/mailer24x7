/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;

/**
 * @author ADMIN
 * 
 */
@Repository("campaignStep2Form")
public class CampaignStep2Form {

	private long campaignId;
	private int contentType;
	private String nextAction;

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
	 * @return the contentType
	 */
	public int getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(int contentType) {
		this.contentType = contentType;
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
