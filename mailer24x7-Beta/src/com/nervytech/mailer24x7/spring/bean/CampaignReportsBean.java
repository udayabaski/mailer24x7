/**
 * 
 */
package com.nervytech.mailer24x7.spring.bean;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author bsikkaya
 * 
 */
@Repository("campaignReportsBean")
public class CampaignReportsBean {

	private String campaignName;
	private long campaignId;
	private String senderName;
	private String senderEmailId;
	private String subscriberListName;
	private String subscriberListId;
	private int totalEmailsSent;
	private int opened;
	private int clicked;
	private int bounced;
	private int unsubscribed;
	private int dropped;
	private int spamCompliants;
	private List<LinkBean> linksList;
	private float openedPercentage;
	private float clickedPercentage;
	private float bouncedPercentage;
	private float unsubscribedPercentage;
	private float spamCompliantsPercentage;
	private float droppedPercentage;
	private String sentTime;

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
	 * @return the senderEmailId
	 */
	public String getSenderEmailId() {
		return senderEmailId;
	}

	/**
	 * @param senderEmailId
	 *            the senderEmailId to set
	 */
	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}

	/**
	 * @return the subscriberListName
	 */
	public String getSubscriberListName() {
		return subscriberListName;
	}

	/**
	 * @param subscriberListName
	 *            the subscriberListName to set
	 */
	public void setSubscriberListName(String subscriberListName) {
		this.subscriberListName = subscriberListName;
	}

	/**
	 * @return the subscriberListId
	 */
	public String getSubscriberListId() {
		return subscriberListId;
	}

	/**
	 * @param subscriberListId
	 *            the subscriberListId to set
	 */
	public void setSubscriberListId(String subscriberListId) {
		this.subscriberListId = subscriberListId;
	}

	/**
	 * @return the totalEmailsSent
	 */
	public int getTotalEmailsSent() {
		return totalEmailsSent;
	}

	/**
	 * @param totalEmailsSent
	 *            the totalEmailsSent to set
	 */
	public void setTotalEmailsSent(int totalEmailsSent) {
		this.totalEmailsSent = totalEmailsSent;
	}

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
	 * @return the bounced
	 */
	public int getBounced() {
		return bounced;
	}

	/**
	 * @param bounced
	 *            the bounced to set
	 */
	public void setBounced(int bounced) {
		this.bounced = bounced;
	}

	/**
	 * @return the unsubscribed
	 */
	public int getUnsubscribed() {
		return unsubscribed;
	}

	/**
	 * @param unsubscribed
	 *            the unsubscribed to set
	 */
	public void setUnsubscribed(int unsubscribed) {
		this.unsubscribed = unsubscribed;
	}

	/**
	 * @return the spamCompliants
	 */
	public int getSpamCompliants() {
		return spamCompliants;
	}

	/**
	 * @param spamCompliants
	 *            the spamCompliants to set
	 */
	public void setSpamCompliants(int spamCompliants) {
		this.spamCompliants = spamCompliants;
	}

	/**
	 * @return the linksList
	 */
	public List<LinkBean> getLinksList() {
		return linksList;
	}

	/**
	 * @param linksList
	 *            the linksList to set
	 */
	public void setLinksList(List<LinkBean> linksList) {
		this.linksList = linksList;
	}

	/**
	 * @return the openedPercentage
	 */
	public float getOpenedPercentage() {
		return openedPercentage;
	}

	/**
	 * @param openedPercentage
	 *            the openedPercentage to set
	 */
	public void setOpenedPercentage(float openedPercentage) {
		this.openedPercentage = openedPercentage;
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

	/**
	 * @return the bouncedPercentage
	 */
	public float getBouncedPercentage() {
		return bouncedPercentage;
	}

	/**
	 * @param bouncedPercentage
	 *            the bouncedPercentage to set
	 */
	public void setBouncedPercentage(float bouncedPercentage) {
		this.bouncedPercentage = bouncedPercentage;
	}

	/**
	 * @return the unsubscribedPercentage
	 */
	public float getUnsubscribedPercentage() {
		return unsubscribedPercentage;
	}

	/**
	 * @param unsubscribedPercentage
	 *            the unsubscribedPercentage to set
	 */
	public void setUnsubscribedPercentage(float unsubscribedPercentage) {
		this.unsubscribedPercentage = unsubscribedPercentage;
	}

	/**
	 * @return the spamCompliantsPercentage
	 */
	public float getSpamCompliantsPercentage() {
		return spamCompliantsPercentage;
	}

	/**
	 * @param spamCompliantsPercentage
	 *            the spamCompliantsPercentage to set
	 */
	public void setSpamCompliantsPercentage(float spamCompliantsPercentage) {
		this.spamCompliantsPercentage = spamCompliantsPercentage;
	}

	/**
	 * @return the sentTime
	 */
	public String getSentTime() {
		return sentTime;
	}

	/**
	 * @param sentTime
	 *            the sentTime to set
	 */
	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}

	/**
	 * @return the dropped
	 */
	public int getDropped() {
		return dropped;
	}

	/**
	 * @param dropped
	 *            the dropped to set
	 */
	public void setDropped(int dropped) {
		this.dropped = dropped;
	}

	/**
	 * @return the droppedPercentage
	 */
	public float getDroppedPercentage() {
		return droppedPercentage;
	}

	/**
	 * @param droppedPercentage
	 *            the droppedPercentage to set
	 */
	public void setDroppedPercentage(float droppedPercentage) {
		this.droppedPercentage = droppedPercentage;
	}

}