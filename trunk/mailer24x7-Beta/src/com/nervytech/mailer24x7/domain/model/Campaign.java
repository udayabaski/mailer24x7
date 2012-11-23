/**
 * 
 */
package com.nervytech.mailer24x7.domain.model;

/**
 * @author bsikkaya
 * 
 */
public class Campaign {

	private long campaignId;
	private long orgId;
	private String campaignName;
	private String createdEmailId;
	private String createdTime;
	private String lastModifiedTime;
	private String isPoweredBy;
	private String subject;
	private String imageLoc;
	private String campaignLink;
	private String unsubscribeLink;
	private String unsubscribeSubject;
	private int campaignType;

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
	 * @return the orgId
	 */
	public long getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(long orgId) {
		this.orgId = orgId;
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
	 * @return the createdEmailId
	 */
	public String getCreatedEmailId() {
		return createdEmailId;
	}

	/**
	 * @param createdEmailId
	 *            the createdEmailId to set
	 */
	public void setCreatedEmailId(String createdEmailId) {
		this.createdEmailId = createdEmailId;
	}

	/**
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime
	 *            the createdTime to set
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the lastModifiedTime
	 */
	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	/**
	 * @param lastModifiedTime
	 *            the lastModifiedTime to set
	 */
	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 * @return the isPoweredBy
	 */
	public String getIsPoweredBy() {
		return isPoweredBy;
	}

	/**
	 * @param isPoweredBy
	 *            the isPoweredBy to set
	 */
	public void setIsPoweredBy(String isPoweredBy) {
		this.isPoweredBy = isPoweredBy;
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
	 * @return the imageLoc
	 */
	public String getImageLoc() {
		return imageLoc;
	}

	/**
	 * @param imageLoc
	 *            the imageLoc to set
	 */
	public void setImageLoc(String imageLoc) {
		this.imageLoc = imageLoc;
	}

	/**
	 * @return the campaignLink
	 */
	public String getCampaignLink() {
		return campaignLink;
	}

	/**
	 * @param campaignLink
	 *            the campaignLink to set
	 */
	public void setCampaignLink(String campaignLink) {
		this.campaignLink = campaignLink;
	}

	/**
	 * @return the unsubscribeLink
	 */
	public String getUnsubscribeLink() {
		return unsubscribeLink;
	}

	/**
	 * @param unsubscribeLink
	 *            the unsubscribeLink to set
	 */
	public void setUnsubscribeLink(String unsubscribeLink) {
		this.unsubscribeLink = unsubscribeLink;
	}

	/**
	 * @return the unsubscribeSubject
	 */
	public String getUnsubscribeSubject() {
		return unsubscribeSubject;
	}

	/**
	 * @param unsubscribeSubject
	 *            the unsubscribeSubject to set
	 */
	public void setUnsubscribeSubject(String unsubscribeSubject) {
		this.unsubscribeSubject = unsubscribeSubject;
	}

	/**
	 * @return the campaignType
	 */
	public int getCampaignType() {
		return campaignType;
	}

	/**
	 * @param campaignType
	 *            the campaignType to set
	 */
	public void setCampaignType(int campaignType) {
		this.campaignType = campaignType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Campaign [campaignId=");
		builder.append(campaignId);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", campaignName=");
		builder.append(campaignName);
		builder.append(", createdEmailId=");
		builder.append(createdEmailId);
		builder.append(", createdTime=");
		builder.append(createdTime);
		builder.append(", lastModifiedTime=");
		builder.append(lastModifiedTime);
		builder.append(", isPoweredBy=");
		builder.append(isPoweredBy);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", imageLoc=");
		builder.append(imageLoc);
		builder.append(", campaignLink=");
		builder.append(campaignLink);
		builder.append(", unsubscribeLink=");
		builder.append(unsubscribeLink);
		builder.append(", unsubscribeSubject=");
		builder.append(unsubscribeSubject);
		builder.append(", campaignType=");
		builder.append(campaignType);
		builder.append("]");
		return builder.toString();
	}

}
