package com.nervytech.mailer24x7.model.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.common.enums.MailgunSyncStatusEnum;
import com.nervytech.mailer24x7.domain.model.CampaignStatus;
import com.nervytech.mailer24x7.mailgun.CampaignStatsVO;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignStatusDAO;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.nervytech.refermejob.hibernate.artifact.Users
 * @author MyEclipse Persistence Tools
 */

@Resource(mappedName = "campaignStatusDAO")
public class CampaignStatusDAO extends JdbcDaoSupport implements
		ICampaignStatusDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignStatusDAO.class);

	public void updateCampaignStatus(long campaignId, int status,
			String sentTime) {
		String udateQuery = "update CAMPAIGN_STATUS set STATUS='" + status
				+ "',SENT_TIME='" + sentTime + "' WHERE CAMPAIGN_ID ="
				+ campaignId;

		logger.debug("Update Campaign Status Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public void updateCampaignSyncStatus(long campaignId,
			MailgunSyncStatusEnum status) {
		String updateQuery = "UPDATE CAMPAIGN_STATUS SET SYNC_STATUS="
				+ status.getStatus() + " WHERE CAMPAIGN_ID = " + campaignId;

		logger.debug("Updating campaign Sync Status : " + updateQuery);

		getJdbcTemplate().execute(updateQuery);
	}

	public void updateCampaignSubscriberStatus(CampaignStatsVO cmpnStatusVO) {
		String updateStatsQuery = "UPDATE CAMPAIGN_STATUS SET TOTAL_SUBSCRIBER_SENT="
				+ cmpnStatusVO.getDelivered()
				+ ", OPENED ="
				+ cmpnStatusVO.getOpens()
				+ ", CLICKED = "
				+ cmpnStatusVO.getClicks()
				+ ", BOUNCED = "
				+ cmpnStatusVO.getBounced()
				+ ", DROPPED = "
				+ cmpnStatusVO.getDropped()
				+ ", UNSUBSCRIBED = "
				+ cmpnStatusVO.getUnsubscribed()
				+ " WHERE CAMPAIGN_ID="
				+ cmpnStatusVO.getCampaignId();

		logger.debug("Update Status Query : " + updateStatsQuery);

		getJdbcTemplate().execute(updateStatsQuery);
	}

	public void updateDeliveredCount(long campaignId, int deliveredCount) {
		String updateQuery = "UPDATE CAMPAIGN_STATUS SET TOTAL_SUBSCRIBER_SENT = "
				+ deliveredCount + " WHERE CAMPAIGN_ID=" + campaignId;

		logger.debug("Update Delivery count Query : " + updateQuery);

		getJdbcTemplate().execute(updateQuery);
	}

	public void updateCampaignEventsStats(CampaignStatsVO campaignStatusVO) {
		String updateStatsQuery = "UPDATE CAMPAIGN_STATUS SET TOTAL_EVENT_FETCHED = TOTAL_EVENT_FETCHED + "
				+ campaignStatusVO.getEventCount()
				+ ", OPENED = OPENED + "
				+ campaignStatusVO.getOpens()
				+ ", CLICKED = CLICKED + "
				+ campaignStatusVO.getClicks()
				+ ", BOUNCED = BOUNCED + "
				+ campaignStatusVO.getBounced()
				+ ", DROPPED = DROPPED + "
				+ campaignStatusVO.getDropped()
				+ ", UNSUBSCRIBED = UNSUBSCRIBED + "
				+ campaignStatusVO.getUnsubscribed()
				+ " WHERE CAMPAIGN_ID="
				+ campaignStatusVO.getCampaignId();

		logger.debug("Update Campaign Status Query : " + updateStatsQuery);

		getJdbcTemplate().execute(updateStatsQuery);

	}

	public int getCampaignEventFetchCount(long campaignId) {
		String selectQuery = "SELECT TOTAL_EVENT_FETCHED FROM CAMPAIGN_STATUS WHERE CAMPAIGN_ID = "
				+ campaignId;

		logger.debug("Campaign Event Fetched Query : " + selectQuery);

		return getJdbcTemplate().queryForInt(selectQuery);
	}

	public void updateLatestCampaignSubscriberId(long campaignId,
			long subscriberId) {
		String insertQuery = "UPDATE CAMPAIGN_STATUS SET LATEST_UPDATED_SUBSCRIBERID="
				+ subscriberId + " WHERE CAMPAIGN_ID = " + campaignId;
		System.out.println("UPDATE Query ===>>> " + insertQuery);
		getJdbcTemplate().execute(insertQuery);
	}

	public long getLatestCampaignSubscriberId(long campaignId) {
		String selectQuery = "SELECT LATEST_UPDATED_SUBSCRIBERID FROM CAMPAIGN_STATUS WHERE CAMPAIGN_ID = "
				+ campaignId;
		System.out.println(" Campaign sync status query ===> " + selectQuery);
		return getJdbcTemplate().queryForLong(selectQuery);
	}

	public void updateCampaignEventFetchCount(long campaignId, int count) {
		String insertQuery = "UPDATE CAMPAIGN_STATUS SET TOTAL_EVENT_FETCHED = "
				+ count + " WHERE CAMPAIGN_ID = " + campaignId;
		System.out.println("UPDATE Query ===>>> " + insertQuery);
		getJdbcTemplate().execute(insertQuery);
	}

	public void updateCampaignStatus(CampaignStatsVO cmpnStatusVO) {
		String updateStatsQuery = "UPDATE CAMPAIGN_STATUS SET TOTAL_SUBSCRIBER_SENT="
				+ cmpnStatusVO.getDelivered()
				+ ", OPENED ="
				+ cmpnStatusVO.getOpens()
				+ ","
				+ " CLICKED = "
				+ cmpnStatusVO.getClicks()
				+ ", BOUNCED = "
				+ cmpnStatusVO.getBounced()
				+ ", DROPPED = "
				+ cmpnStatusVO.getDropped()
				+ ", UNSUBSCRIBED = "
				+ cmpnStatusVO.getUnsubscribed()
				+ " WHERE CAMPAIGN_ID="
				+ cmpnStatusVO.getCampaignId();

		logger.debug("Update Status Query : " + updateStatsQuery);

		getJdbcTemplate().execute(updateStatsQuery);
	}

	public void saveCampaignStatus(CampaignStatus cmpnStatus) {

		String insertQuery = "INSERT INTO CAMPAIGN_STATUS (CAMPAIGN_ID,ORG_ID,USER_ID,SENDER_ID,"
				+ " STATUS,LATEST_SUBSCRIBER_COUNT,SYNC_STATUS,S3_PATH,LAST_UPDATED_TIME,LATEST_UPDATED_SUBSCRIBERID) "
				+ " VALUES('"
				+ cmpnStatus.getCampaignId()
				+ "','"
				+ cmpnStatus.getOrgId()
				+ "','"
				+ cmpnStatus.getUserId()
				+ "','"
				+ cmpnStatus.getSenderId()
				+ "','"
				+ cmpnStatus.getStatus()
				+ "','"
				+ cmpnStatus.getLatestSubscriberCount()
				+ "','"
				+ cmpnStatus.getSyncStatus()
				+ "','"
				+ cmpnStatus.getS3Path()
				+ "','"
				+ cmpnStatus.getLastUpdatedTime()
				+ "','"
				+ cmpnStatus.getLatestUpdatedSubscriberId() + "')";

		logger.debug("Save Campaign Status Query : " + insertQuery);

		getJdbcTemplate().execute(insertQuery);

	}

	@Override
	public void updateCampaignStatusSender(CampaignStatus cmpnStatus) {
		String updateQuery = "UPDATE CAMPAIGN_STATUS SET SENDER_ID="
				+ cmpnStatus.getSenderId() + ", LAST_UPDATED_TIME='"
				+ cmpnStatus.getLastUpdatedTime() + "' WHERE CAMPAIGN_ID="
				+ cmpnStatus.getCampaignId();

		System.out.println("UPDATE QUERYYYYYYYYY " + updateQuery);
		logger.debug("Update Campaign Status Query : " + updateQuery);

		getJdbcTemplate().execute(updateQuery);
	}

	@Override
	public void updateS3Path(String s3Path, long campaignId, String time) {
		String updateQuery = "UPDATE CAMPAIGN_STATUS SET S3_PATH='" + s3Path
				+ "', LAST_UPDATED_TIME='" + time + "' WHERE CAMPAIGN_ID="
				+ campaignId;

		logger.debug("Update Campaign Status Query : " + updateQuery);

		getJdbcTemplate().execute(updateQuery);

	}

	@Override
	public void updateSubscriberListId(long campaignId, String subscriberGroup) {
		String updateQuery = "UPDATE CAMPAIGN_STATUS SET SUBSCRIBER_LIST_ID='"
				+ subscriberGroup + "' WHERE CAMPAIGN_ID=" + campaignId;

		logger.debug("Update Campaign Query : " + updateQuery);

		getJdbcTemplate().execute(updateQuery);

	}

	public static CampaignStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CampaignStatusDAO) ctx.getBean("CampaignStatusDAO");
	}

	@Override
	public String getS3Path(String campaignId) {
		String selectQuery = "SELECT S3_PATH FROM CAMPAIGN_STATUS WHERE CAMPAIGN_ID = "
				+ campaignId;
		System.out.println(" Campaign sync status query ===> " + selectQuery);
		return getJdbcTemplate().queryForObject(selectQuery, String.class);
	}

	@Override
	public void scheduleCampaignNow(String campaignId, int status,String confirmationMail,
			String updateTime) {
		String udateQuery = "update CAMPAIGN_STATUS set STATUS='" + status
				+ "',CONFIRMATION_MAILID='"+confirmationMail+"', LAST_UPDATED_TIME='" + updateTime
				+ "' WHERE CAMPAIGN_ID =" + campaignId;

		logger.debug("Update Campaign Status Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	@Override
	public void scheduleCampaignLater(String campaignId, int status,
			String confirmationMailIdLater, String scheduledTime,
			String timezone, String updatedTime) {
		String udateQuery = "update CAMPAIGN_STATUS set STATUS='" + status
				+ "',CONFIRMATION_MAILID='"+confirmationMailIdLater+"', LAST_UPDATED_TIME='" + updatedTime
				+ "',SCHEDULED_ON='"+scheduledTime+"'  TIMEZONE='" + timezone
				+ "' WHERE CAMPAIGN_ID =" + campaignId;

		logger.debug("Update Campaign Status Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

}