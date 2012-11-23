package com.nervytech.mailer24x7.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.common.enums.CampaignStatusEnum;
import com.nervytech.mailer24x7.domain.model.Campaign;
import com.nervytech.mailer24x7.domain.model.CampaignSchedulerModel;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignDAO;
import com.nervytech.mailer24x7.spring.bean.CampaignBean;
import com.nervytech.mailer24x7.spring.bean.CampaignSnapshotBean;

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

@Resource(mappedName = "campaignDAO")
public class CampaignDAO extends JdbcDaoSupport implements ICampaignDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignDAO.class);

	public List<Campaign> getCampaignsToSyncBiHoursStats(int status,
			String time, long lowerLimit, long upperLimit) {

		RowMapper<Campaign> mapper = new RowMapper<Campaign>() {
			public Campaign mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Campaign cmpn = new Campaign();

				cmpn.setCampaignId(rs.getLong("CAMPAIGN_ID"));
				cmpn.setCampaignName(rs.getString("CAMPAIGN_NAME"));

				return cmpn;
			}
		};

		String selectCampaignQuery = "SELECT C.CAMPAIGN_ID,C.CAMPAIGN_NAME "
				+ " FROM CAMPAIGN C, CAMPAIGN_STATUS CS "
				+ " WHERE C.CAMPAIGN_ID=CS.CAMPAIGN_ID AND CS.SENT_TIME >= '"
				+ time + "' ORDER BY C.CAMPAIGN_ID ASC LIMIT " + lowerLimit
				+ "," + upperLimit;

		logger.debug("Schedule Campaign Query : " + selectCampaignQuery);

		return getJdbcTemplate().query(selectCampaignQuery, mapper);
	}

	public static CampaignDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CampaignDAO) ctx.getBean("CampaignDAO");
	}

	@Override
	public List<CampaignBean> getLatestCampaigns(int count, long orgId) {
		RowMapper<CampaignBean> mapper = new RowMapper<CampaignBean>() {
			public CampaignBean mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				CampaignBean cmpn = new CampaignBean();

				cmpn.setCampaignId(rs.getLong("CAMPAIGN_ID"));
				cmpn.setCampaignName(rs.getString("CAMPAIGN_NAME"));
				cmpn.setCampaignStatus(CampaignStatusEnum.values()[rs
						.getInt("STATUS")].name());
				cmpn.setCreatedBy(rs.getString("CREATED_EMAILID"));
				cmpn.setCreatedDate(rs.getString("CREATED_TIME"));

				return cmpn;
			}
		};

		String selectCampaignQuery = "SELECT C.CAMPAIGN_ID,C.CAMPAIGN_NAME,C.CREATED_EMAILID,C.CREATED_TIME,CS.STATUS "
				+ " FROM CAMPAIGN C,CAMPAIGN_STATUS CS"
				+ " WHERE C.CAMPAIGN_ID=CS.CAMPAIGN_ID C.ORG_ID="
				+ orgId
				+ " ORDER BY C.CREATED_TIME DSC LIMIT " + count;

		logger.debug("Schedule Campaign Query : " + selectCampaignQuery);

		return getJdbcTemplate().query(selectCampaignQuery, mapper);
	}

	public List<CampaignBean> getCampaigns(long orgId, int status) {

		RowMapper mapper = new RowMapper() {
			public CampaignBean mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				CampaignBean cmpn = new CampaignBean();

				cmpn.setCampaignId(rs.getLong("CAMPAIGN_ID"));
				cmpn.setCampaignName(rs.getString("CAMPAIGN_NAME"));
				cmpn.setCreatedBy(rs.getString("CREATED_EMAILID"));
				cmpn.setLastModifiedTime(rs.getString("LAST_MODIFIED_TIME"));
				cmpn.setSubject(rs.getString("SUBJECT"));
				cmpn.setOpened(rs.getInt("OPENED"));
				cmpn.setBounced(rs.getInt("BOUNCED"));
				cmpn.setClicked(rs.getInt("CLICKED"));
				cmpn.setScheduledOn(rs.getString("SCHEDULED_ON"));

				return cmpn;
			}
		};

		String selectCampaignQuery = "SELECT C.CAMPAIGN_ID,C.CAMPAIGN_NAME,C.CREATED_EMAILID,C.LAST_MODIFIED_TIME,C.SUBJECT, CS.OPENED,CS.BOUNCED,CS.CLICKED,"
				+ " CS.SCHEDULED_ON FROM CAMPAIGN C,CAMPAIGN_STATUS CS "
				+ " WHERE C.CAMPAIGN_ID=CS.CAMPAIGN_ID AND CS.STATUS="
				+ status
				+ " AND C.ORG_ID=" + orgId;

		System.out.println("MAIL ID Query ===>>>> " + selectCampaignQuery);
		return getJdbcTemplate().query(selectCampaignQuery, mapper);
	}

	public void deleteCampaign(long campaignId) {

		String deleteQuery = "DELETE FROM CAMPAIGN_STATUS WHERE CAMPAIGN_ID="
				+ campaignId;

		logger.debug("Delete Campaign Status Query : " + deleteQuery);

		getJdbcTemplate().execute(deleteQuery);

		deleteQuery = "DELETE FROM SUBSCRIBER_REPORTS WHERE CAMPAIGN_ID="
				+ campaignId;

		logger.debug("Delete Subscriber Reports Query : " + deleteQuery);

		getJdbcTemplate().execute(deleteQuery);

		deleteQuery = "DELETE FROM CAMPAIGN WHERE CAMPAIGN_ID=" + campaignId;

		logger.debug("Delete Campaign Query : " + deleteQuery);

		getJdbcTemplate().execute(deleteQuery);
	}

	@Override
	public long getOrgId(long campaignId) {

		String getCampaignQuery = "SELECT ORG_ID FROM CAMPAIGN WHERE CAMPAIGN_ID="
				+ campaignId;

		logger.debug("Check Subscriber List Query : " + getCampaignQuery);

		return getJdbcTemplate().queryForLong(getCampaignQuery);

	}

	@Override
	public List<CampaignSnapshotBean> getCampaign(long campaignId) {
		RowMapper<CampaignSnapshotBean> mapper = new RowMapper<CampaignSnapshotBean>() {
			public CampaignSnapshotBean mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				CampaignSnapshotBean cmpn = new CampaignSnapshotBean();

				cmpn.setCampaignId(rs.getLong("CAMPAIGN_ID"));
				cmpn.setCampaignName(rs.getString("CAMPAIGN_NAME"));
				cmpn.setCampaignStatus(CampaignStatusEnum.values()[rs
						.getInt("STATUS")].name());
				cmpn.setSubject(rs.getString("SUBJECT"));
				cmpn.setSenderEmailId(rs.getString("EMAIL_ID"));
				cmpn.setSenderName(rs.getString("DISPLAY_NAME"));
				cmpn.setSubscriberListId(rs.getString("SUBSCRIBER_LIST_ID"));
				cmpn.setSubscriberListName(rs.getString("SUBSCRIBER_LIST_NAME"));
				cmpn.setSenderId(rs.getString("SENDER_ID"));

				return cmpn;
			}
		};

		String selectCampaignQuery = "SELECT C.CAMPAIGN_NAME,C.SUBJECT,C.CREATED_TIME,CS.STATUS, "
				+ " CSR.DISPLAY_NAME,CSR.EMAIL_ID, CS.SUBSCRIBER_LIST_ID, SL.SUBSCRIBER_LIST_ID,"
				+ " CS.SENDER_ID,SL.SUBSCRIBER_LIST_NAME"
				+ " FROM CAMPAIGN C,CAMPAIGN_STATUS CS,CAMPAIGN_SENDER CSR,SUBSCRIBER_LIST SL"
				+ " WHERE C.CAMPAIGN_ID=CS.CAMPAIGN_ID AND CS.SENDER_ID=CSR.SENDER_ID AND "
				+ " CS.SUBSCRIBER_LIST_ID=SL.SUBSCRIBER_LIST_ID AND C.CAMPAIGN_ID="
				+ campaignId;

		logger.debug("Schedule Campaign Query : " + selectCampaignQuery);

		return getJdbcTemplate().query(selectCampaignQuery, mapper);
	}

	public long saveCampaign(Campaign cmpn) {

		String insertQuery = "INSERT INTO CAMPAIGN (ORG_ID,CAMPAIGN_NAME,CREATED_EMAILID,CREATED_TIME,LAST_MODIFIED_TIME,"
				+ "ISPOWEREDBY,SUBJECT,IMAGE_LOC,CAMPAIGN_LINK,UNSUBSCRIBE_LINK,UNSUBSCRIBE_SUBJECT) "
				+ " VALUES('"
				+ cmpn.getOrgId()
				+ "','"
				+ cmpn.getCampaignName()
				+ "','"
				+ cmpn.getCreatedEmailId()
				+ "','"
				+ cmpn.getCreatedTime()
				+ "',"
				+ "'"
				+ cmpn.getLastModifiedTime()
				+ "','"
				+ cmpn.getIsPoweredBy()
				+ "','"
				+ cmpn.getSubject()
				+ "','"
				+ cmpn.getImageLoc()
				+ "',"
				+ "'"
				+ cmpn.getCampaignLink()
				+ "','"
				+ cmpn.getUnsubscribeLink()
				+ "','"
				+ cmpn.getUnsubscribeSubject() + "')";

		logger.debug("Save campaign Query : " + insertQuery);

		getJdbcTemplate().execute(insertQuery);

		String selectQuery = "SELECT CAMPAIGN_ID FROM CAMPAIGN WHERE ORG_ID='"
				+ cmpn.getOrgId() + "' AND CAMPAIGN_NAME='"
				+ cmpn.getCampaignName() + "' AND SUBJECT='"
				+ cmpn.getSubject() + "' AND " + " CREATED_TIME='"
				+ cmpn.getCreatedTime() + "'";

		logger.debug("Select Campaign Query : " + selectQuery);

		return getJdbcTemplate().queryForLong(selectQuery);
	}

	@Override
	public void updateCampaign(Campaign cmpn) {
		String updateQuery = "UPDATE CAMPAIGN SET CAMPAIGN_NAME='"
				+ cmpn.getCampaignName() + "',LAST_MODIFIED_TIME='"
				+ cmpn.getLastModifiedTime() + "'," + "SUBJECT='"
				+ cmpn.getSubject() + "',IMAGE_LOC='" + cmpn.getImageLoc()
				+ "' WHERE CAMPAIGN_ID=" + cmpn.getCampaignId();

		logger.debug("Update Campaign Query : " + updateQuery);

		getJdbcTemplate().execute(updateQuery);

	}

	@Override
	public void updateContentType(long campaignId, int campaignType) {
		String updateQuery = "UPDATE CAMPAIGN SET CAMPAIGN_TYPE='"
				+ campaignType + "'" + "' WHERE CAMPAIGN_ID=" + campaignId;

		logger.debug("Update Campaign Query : " + updateQuery);

		getJdbcTemplate().execute(updateQuery);
	}

	public List<CampaignSchedulerModel> getScheduledCampaigns(int status,
			String toTime, int rowCounts) {

		RowMapper<CampaignSchedulerModel> mapper = new RowMapper<CampaignSchedulerModel>() {
			public CampaignSchedulerModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				CampaignSchedulerModel cmpn = new CampaignSchedulerModel();

				cmpn.setCampaignId(rs.getLong("CAMPAIGN_ID"));
				cmpn.setCampaignName(rs.getString("CAMPAIGN_NAME"));
				cmpn.setSubject(rs.getString("SUBJECT"));
				cmpn.setUnsubscribeLink(rs.getString("UNSUBSCRIBE_LINK"));
				cmpn.setUnsubscribeSubject(rs.getString("UNSUBSCRIBE_SUBJECT"));
				cmpn.setSubscriberListId(rs.getLong("SUBSCRIBER_LIST_ID"));
				// cmpn.setStatusId(rs.getLong("STATUS_ID"));
				cmpn.setLatestSubscriberId(rs
						.getLong("LATEST_UPDATED_SUBSCRIBERID"));
				cmpn.setS3Path(rs.getString("S3_PATH"));
				cmpn.setSenderEmailId(rs.getString("EMAIL_ID"));
				cmpn.setOrgId(rs.getLong("ORG_ID"));
				cmpn.setSyncStatus(rs.getInt("SYNC_STATUS"));
				cmpn.setCampaignType(rs.getInt("CAMPAIGN_TYPE"));

				return cmpn;
			}
		};

		String selectCampaignQuery = "SELECT C.CAMPAIGN_ID,C.ORG_ID,C.CAMPAIGN_NAME,C.SUBJECT,C.UNSUBSCRIBE_LINK,C.UNSUBSCRIBE_SUBJECT,"
				+ " CS.SUBSCRIBER_LIST_ID,CS.LATEST_SUBSCRIBER_COUNT,C.IMAGE_LOC,CS.SYNC_STATUS,CS.ORG_ID, CSR.EMAIL_ID, CS.S3_PATH "
				+ " C.CAMPAIGN_TYPE "
				+ " FROM CAMPAIGN C, CAMPAIGN_STATUS CS, CAMPAIGN_SENDER CSR "
				+ " WHERE C.CAMPAIGN_ID=CS.CAMPAIGN_ID AND CS.SENDER_ID=CSR.SENDER_ID AND CS.STATUS="
				+ status
				+ " AND CS.SCHEDULED_ON <='"
				+ toTime
				+ "' ORDER BY CS.SCHEDULED_ON ASC LIMIT " + rowCounts;

		logger.debug("Schedule Campaign Query : " + selectCampaignQuery);

		return getJdbcTemplate().query(selectCampaignQuery, mapper);
	}

	public List<CampaignSchedulerModel> getScheduledCampaigns(int status,
			int rowCounts) {

		RowMapper<CampaignSchedulerModel> mapper = new RowMapper<CampaignSchedulerModel>() {
			public CampaignSchedulerModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				CampaignSchedulerModel cmpn = new CampaignSchedulerModel();

				cmpn.setCampaignId(rs.getLong("CAMPAIGN_ID"));
				cmpn.setCampaignName(rs.getString("CAMPAIGN_NAME"));
				cmpn.setSubject(rs.getString("SUBJECT"));
				cmpn.setUnsubscribeLink(rs.getString("UNSUBSCRIBE_LINK"));
				cmpn.setUnsubscribeSubject(rs.getString("UNSUBSCRIBE_SUBJECT"));
				cmpn.setSubscriberListId(rs.getLong("SUBSCRIBER_LIST_ID"));
				// cmpn.setStatusId(rs.getLong("STATUS_ID"));
				cmpn.setLatestSubscriberId(rs
						.getLong("LATEST_UPDATED_SUBSCRIBERID"));
				cmpn.setS3Path(rs.getString("S3_PATH"));
				cmpn.setSenderEmailId(rs.getString("EMAIL_ID"));
				cmpn.setOrgId(rs.getLong("ORG_ID"));
				cmpn.setSyncStatus(rs.getInt("SYNC_STATUS"));
				cmpn.setCampaignType(rs.getInt("SYNC_STATUS"));

				return cmpn;
			}
		};

		String selectCampaignQuery = "SELECT C.CAMPAIGN_ID,C.CAMPAIGN_NAME,C.SUBJECT,C.UNSUBSCRIBE_LINK,C.UNSUBSCRIBE_SUBJECT,"
				+ " CS.SUBSCRIBER_LIST_ID,CS.LATEST_SUBSCRIBER_COUNT,CS.LATEST_UPDATED_SUBSCRIBERID,C.IMAGE_LOC,CS.SYNC_STATUS,CS.ORG_ID, CSR.EMAIL_ID "
				+ " C.CAMPAIGN_TYPE,CS.s3_path "
				+ " FROM CAMPAIGN C, CAMPAIGN_STATUS CS, CAMPAIGN_SENDER CSR "
				+ " WHERE C.CAMPAIGN_ID=CS.CAMPAIGN_ID AND CS.SENDER_ID=CSR.SENDER_ID AND CS.STATUS= "
				+ status + " ORDER BY CS.SCHEDULED_ON ASC LIMIT " + rowCounts;

		logger.debug("Schedule Campaign Query : " + selectCampaignQuery);

		return getJdbcTemplate().query(selectCampaignQuery, mapper);
	}

	public List<Campaign> getCampaignsToSyncTwelveHoursStats(int status,
			String time, long lowerLimit, long upperLimit) {

		RowMapper<Campaign> mapper = new RowMapper<Campaign>() {
			public Campaign mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Campaign cmpn = new Campaign();

				cmpn.setCampaignId(rs.getLong("CAMPAIGN_ID"));
				cmpn.setCampaignName(rs.getString("CAMPAIGN_NAME"));

				return cmpn;
			}
		};

		String selectCampaignQuery = "SELECT C.CAMPAIGN_ID,C.CAMPAIGN_NAME "
				+ " FROM CAMPAIGN C, CAMPAIGN_STATUS CS "
				+ " WHERE C.CAMPAIGN_ID=CS.CAMPAIGN_ID AND CS.SENT_TIME <= '"
				+ time + "' ORDER BY C.CAMPAIGN_ID ASC LIMIT " + lowerLimit
				+ "," + upperLimit;

		logger.debug("Schedule Campaign Query : " + selectCampaignQuery);

		return getJdbcTemplate().query(selectCampaignQuery, mapper);
	}

}