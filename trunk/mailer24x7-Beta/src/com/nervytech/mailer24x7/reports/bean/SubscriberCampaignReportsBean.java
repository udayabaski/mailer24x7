/**
 * 
 */
package com.nervytech.mailer24x7.reports.bean;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author bsikkaya
 * 
 */
@Repository("subscriberCampaignReportsBean")
public class SubscriberCampaignReportsBean {

	private int opened;
	private int clicked;
	private int emailsSent;
	private String groupName;
	private String fullName;
	private String subscriberStatus;
	private String emailId;
	private long listId;
	private float opendPercentage;
	private float clickedPercentage;

	private List<SubscriberCampaignBean> campaignsList;

	/**
	 * @return the opened
	 */
	public int getOpened() {
		return opened;
	}

	/**
	 * @param opened
	 *            the opened to set
	 */
	public void setOpened(int opened) {
		this.opened = opened;
	}

	/**
	 * @return the clicked
	 */
	public int getClicked() {
		return clicked;
	}

	/**
	 * @param clicked
	 *            the clicked to set
	 */
	public void setClicked(int clicked) {
		this.clicked = clicked;
	}

	/**
	 * @return the emailsSent
	 */
	public int getEmailsSent() {
		return emailsSent;
	}

	/**
	 * @param emailsSent
	 *            the emailsSent to set
	 */
	public void setEmailsSent(int emailsSent) {
		this.emailsSent = emailsSent;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the subscriberStatus
	 */
	public String getSubscriberStatus() {
		return subscriberStatus;
	}

	/**
	 * @param subscriberStatus
	 *            the subscriberStatus to set
	 */
	public void setSubscriberStatus(String subscriberStatus) {
		this.subscriberStatus = subscriberStatus;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the listId
	 */
	public long getListId() {
		return listId;
	}

	/**
	 * @param listId
	 *            the listId to set
	 */
	public void setListId(long listId) {
		this.listId = listId;
	}

	/**
	 * @return the campaignsList
	 */
	public List<SubscriberCampaignBean> getCampaignsList() {
		return campaignsList;
	}

	/**
	 * @param campaignsList
	 *            the campaignsList to set
	 */
	public void setCampaignsList(List<SubscriberCampaignBean> campaignsList) {
		this.campaignsList = campaignsList;
	}

	/**
	 * @return the opendPercentage
	 */
	public float getOpendPercentage() {
		return opendPercentage;
	}

	/**
	 * @param opendPercentage
	 *            the opendPercentage to set
	 */
	public void setOpendPercentage(float opendPercentage) {
		this.opendPercentage = opendPercentage;
	}

	/**
	 * @return the clickedPercentage
	 */
	public float getClickedPercentage() {
		return clickedPercentage;
	}

	/**
	 * @param clickedPercentage
	 *            the clickedPercentage to set
	 */
	public void setClickedPercentage(float clickedPercentage) {
		this.clickedPercentage = clickedPercentage;
	}

}