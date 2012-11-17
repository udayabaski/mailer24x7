package com.nervytech.mailer24x7.common.enums;

public enum SubscriberCampaignStatusEnum {
	SENT(0), OPENED(1), CLICKED(2), UNSUBSCRIBED(3), BOUNCED(4), DROPPED(5), COMPLAINED(
			6);

	int status;

	private SubscriberCampaignStatusEnum(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
