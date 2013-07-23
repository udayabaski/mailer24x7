/**
 * 
 */
package com.nervytech.mailer24x7.domain.model;

/**
 * @author bsikkaya
 * 
 */
public class SubscriberReports {

	private long id;
	private long campaignId;
	private long subscriberListId;
	private String emailId;
	private int status;
	private long timeInLong;
	private String city;
	private String region;
	private String ip;
	private String country;
	private long subscriberId;
	private int count;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the subscriberListId
	 */
	public long getSubscriberListId() {
		return subscriberListId;
	}

	/**
	 * @param subscriberListId
	 *            the subscriberListId to set
	 */
	public void setSubscriberListId(long subscriberListId) {
		this.subscriberListId = subscriberListId;
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the timeInLong
	 */
	public long getTimeInLong() {
		return timeInLong;
	}

	/**
	 * @param timeInLong
	 *            the timeInLong to set
	 */
	public void setTimeInLong(long timeInLong) {
		this.timeInLong = timeInLong;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the subscriberId
	 */
	public long getSubscriberId() {
		return subscriberId;
	}

	/**
	 * @param subscriberId
	 *            the subscriberId to set
	 */
	public void setSubscriberId(long subscriberId) {
		this.subscriberId = subscriberId;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscriberReports [id=");
		builder.append(id);
		builder.append(", campaignId=");
		builder.append(campaignId);
		builder.append(", subscriberListId=");
		builder.append(subscriberListId);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", timeInLong=");
		builder.append(timeInLong);
		builder.append(", city=");
		builder.append(city);
		builder.append(", region=");
		builder.append(region);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", country=");
		builder.append(country);
		builder.append("]");
		return builder.toString();
	}

}
