/**
 * 
 */
package com.nervytech.mailer24x7.spring.form;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author ADMIN
 * 
 */
@Repository("subscriberForm")
public class SubscriberForm {

	private String subscriberName;

	private String addOption;

	private CommonsMultipartFile fileData;

	private String subscribers;

	private String action;

	private String toPage;

	private int subscriberType;

	private String[] activeSubscribersSelected;

	private String[] bouncedSubscribersSelected;

	private String[] unsubscribedSubscribersSelected;

	private long subscriberListId;

	private int moveTo;

	private long campaignId;

	public int getMoveTo() {
		return moveTo;
	}

	public void setMoveTo(int moveTo) {
		this.moveTo = moveTo;
	}

	public long getSubscriberListId() {
		return subscriberListId;
	}

	public void setSubscriberListId(long subscriberListId) {
		this.subscriberListId = subscriberListId;
	}

	public int getSubscriberType() {
		return subscriberType;
	}

	public void setSubscriberType(int subscriberType) {
		this.subscriberType = subscriberType;
	}

	public String[] getActiveSubscribersSelected() {
		return activeSubscribersSelected;
	}

	public void setActiveSubscribersSelected(String[] activeSubscribersSelected) {
		this.activeSubscribersSelected = activeSubscribersSelected;
	}

	public String[] getBouncedSubscribersSelected() {
		return bouncedSubscribersSelected;
	}

	public void setBouncedSubscribersSelected(
			String[] bouncedSubscribersSelected) {
		this.bouncedSubscribersSelected = bouncedSubscribersSelected;
	}

	public String[] getUnsubscribedSubscribersSelected() {
		return unsubscribedSubscribersSelected;
	}

	public void setUnsubscribedSubscribersSelected(
			String[] unsubscribedSubscribersSelected) {
		this.unsubscribedSubscribersSelected = unsubscribedSubscribersSelected;
	}

	public String getToPage() {
		return toPage;
	}

	public void setToPage(String toPage) {
		this.toPage = toPage;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getAddOption() {
		return addOption;
	}

	public void setAddOption(String addOption) {
		this.addOption = addOption;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	public String getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(String subscribers) {
		this.subscribers = subscribers;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

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

}