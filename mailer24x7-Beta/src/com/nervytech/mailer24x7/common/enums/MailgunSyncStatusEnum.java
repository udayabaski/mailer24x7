package com.nervytech.mailer24x7.common.enums;

public enum MailgunSyncStatusEnum {
	NONE(0), CAMPAIGN_CREATED(1), SUBSCRIBER_LIST_CREATED(2), SUBSCRIBER_UPDATE_IN_PROGRESS(
			3), SUBSCRIBER_UPDATE_COMPLETED(4);

	int status;

	private MailgunSyncStatusEnum(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
