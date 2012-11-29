/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;

/**
 * @author ADMIN
 * 
 */
@Repository("campaignStep1Form")
public class CampaignStep1Form {

	private String campaignName;
	private String subject;
	private String senderName;
	private String senderEmail;
	private String replyToAddress;
	private boolean addGoogleAnalytics;
	private boolean personalizeToAddress;
	private String nextAction;
	private String campaignId;
	private String toPage;

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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName
	 *            the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the senderEmail
	 */
	public String getSenderEmail() {
		return senderEmail;
	}

	/**
	 * @param senderEmail
	 *            the senderEmail to set
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	/**
	 * @return the replyToAddress
	 */
	public String getReplyToAddress() {
		return replyToAddress;
	}

	/**
	 * @param replyToAddress
	 *            the replyToAddress to set
	 */
	public void setReplyToAddress(String replyToAddress) {
		this.replyToAddress = replyToAddress;
	}

	/**
	 * @return the addGoogleAnalytics
	 */
	public boolean isAddGoogleAnalytics() {
		return addGoogleAnalytics;
	}

	/**
	 * @param addGoogleAnalytics
	 *            the addGoogleAnalytics to set
	 */
	public void setAddGoogleAnalytics(boolean addGoogleAnalytics) {
		this.addGoogleAnalytics = addGoogleAnalytics;
	}

	/**
	 * @return the personalizeToAddress
	 */
	public boolean isPersonalizeToAddress() {
		return personalizeToAddress;
	}

	/**
	 * @param personalizeToAddress
	 *            the personalizeToAddress to set
	 */
	public void setPersonalizeToAddress(boolean personalizeToAddress) {
		this.personalizeToAddress = personalizeToAddress;
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

	/**
	 * @return the toPage
	 */
	public String getToPage() {
		return toPage;
	}

	/**
	 * @param toPage
	 *            the toPage to set
	 */
	public void setToPage(String toPage) {
		this.toPage = toPage;
	}

}
