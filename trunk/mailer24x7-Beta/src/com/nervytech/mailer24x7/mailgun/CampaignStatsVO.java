package com.nervytech.mailer24x7.mailgun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nervytech.mailer24x7.common.enums.CampaignJSONKeyEnum;
import com.nervytech.mailer24x7.common.enums.SubscriberCampaignStatusEnum;

public class CampaignStatsVO {
	
	private long campaignId;
	private String campaignName = null;
	
	private int complained;
	private int dropped;
	private int delivered;
	private int opens;
	private int clicks;
	private int bounced;
	private int unsubscribed;
	private int eventCount;
	private Map<Long, List<String>> bounceSubMap = new HashMap<Long, List<String>>();
	private Map<Long, List<String>> unsubscribeSubMap = new HashMap<Long, List<String>>();
	
	public CampaignStatsVO(long campaignId, String campaignName)
	{
		this.campaignId = campaignId;
		this.campaignName = campaignName;
	}

	public long getCampaignId() {
		return campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public int getComplained() {
		return complained;
	}

	public void setComplained(int complained) {
		this.complained = complained;
	}
	
	public void incrementComplained()
	{
		this.complained++;
	}

	public int getDropped() {
		return dropped;
	}

	public void setDropped(int dropped) {
		this.dropped = dropped;
	}
	
	public void incrementDropped()
	{
		this.dropped++;
	}

	public int getDelivered() {
		return delivered;
	}

	public void setDelivered(int delivered) {
		this.delivered = delivered;
	}
	
	public void incrementDelivered()
	{
		this.delivered++;
	}

	public int getOpens() {
		return opens;
	}
	
	public void incrementOpens()
	{
		this.opens++;
	}

	public void setOpens(int opens) {
		this.opens = opens;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	
	public void incrementClicks()
	{
		this.clicks++;
	}

	public int getBounced() {
		return bounced;
	}

	public void setBounced(int bounced) {
		this.bounced = bounced;
	}
	
	public void incrementBounced()
	{
		this.bounced++;
	}

	public int getUnsubscribed() {
		return unsubscribed;
	}

	public void setUnsubscribed(int unsubscribed) {
		this.unsubscribed = unsubscribed;
	}
	
	public void incrementUnsubscribed()
	{
		this.unsubscribed++;
	}
	
	public void addToBounceList(long subscriberId, String emailId)
	{
		List<String> bounceList = bounceSubMap.get(subscriberId);
		if(bounceList == null) {
			bounceList = new ArrayList<String>();
			bounceList.add(emailId);
			bounceSubMap.put(subscriberId, bounceList);
		}else {
			bounceList.add(emailId);
		}
	}
	
	public void addToUnsubscribeList(long subscriberId, String emailId)
	{
		List<String> unsubscribeList = unsubscribeSubMap.get(subscriberId);
		if(unsubscribeList == null) {
			unsubscribeList = new ArrayList<String>();
			unsubscribeList.add(emailId);
			unsubscribeSubMap.put(subscriberId, unsubscribeList);
		}else {
			unsubscribeList.add(emailId);
		}
	}
	
	public Map<Long, List<String>> getBounceSubMap() {
		return bounceSubMap;
	}

	public Map<Long, List<String>> getUnsubscribeSubMap() {
		return unsubscribeSubMap;
	}

	public void incrementEventCount(int status)
	{
        if(status == SubscriberCampaignStatusEnum.OPENED.getStatus()) {
        	incrementOpens();
		}else if(status == SubscriberCampaignStatusEnum.CLICKED.getStatus()) {
			incrementClicks();
		}else if(status == SubscriberCampaignStatusEnum.BOUNCED.getStatus()) {
			incrementBounced();
		}else if(status == SubscriberCampaignStatusEnum.UNSUBSCRIBED.getStatus()) {
			incrementUnsubscribed();
		}else if(status == SubscriberCampaignStatusEnum.CLICKED.getStatus()) {
			incrementClicks();
		}else if(status == SubscriberCampaignStatusEnum.COMPLAINED.getStatus()) {
			incrementComplained();
		}else if(status == SubscriberCampaignStatusEnum.SENT.getStatus()) {
			incrementDelivered();
		}
	}
	
	
	public static void main(String args[]) {
		System.out.println("NNNNNNNNNNNNNNNNN "+CampaignJSONKeyEnum.total.name()+"   TOOOOOOOOOO "+CampaignJSONKeyEnum.total.toString());
	}
	
}
