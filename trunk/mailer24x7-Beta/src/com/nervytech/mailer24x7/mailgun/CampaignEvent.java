package com.nervytech.mailer24x7.mailgun;

public class CampaignEvent {
	
	private int eventStatus;
	
	private String email;
	
	private long eventTime;
	
	private long subscriberId;
	
	private String city;
	
	private String region;
	
	private String ip;
	
	private String country;

	public CampaignEvent(long subscriberId,int eventStatus, String email, long eventTime,String city,String region,String ip,String country)
	{
		this.subscriberId = subscriberId;
		this.eventStatus = eventStatus;
		this.email = email;
		this.eventTime = eventTime;
		this.city = city;
		this.region = region;
		this.ip = ip;
		this.country = country;
	}

	public int getEventStatus() {
		return eventStatus;
	}

	public String getEmail() {
		return email;
	}

	public long getEventTime() {
		return eventTime;
	}
	
	public long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
