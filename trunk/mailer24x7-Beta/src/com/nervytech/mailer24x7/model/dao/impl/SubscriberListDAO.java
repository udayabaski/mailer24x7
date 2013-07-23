package com.nervytech.mailer24x7.model.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.common.enums.SubscriberCampaignStatusEnum;
import com.nervytech.mailer24x7.common.enums.SubscriberStatusEnum;
import com.nervytech.mailer24x7.domain.model.SubscriberList;
import com.nervytech.mailer24x7.domain.model.SubscriberReports;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberListDAO;
import com.nervytech.mailer24x7.reports.bean.SubscriberCampaignBean;
import com.nervytech.mailer24x7.reports.bean.SubscriberCampaignReportsBean;

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

@Resource(mappedName = "subscriberListDAO")
public class SubscriberListDAO extends JdbcDaoSupport implements
		ISubscriberListDAO {
	
	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	
	private static final Logger logger = LoggerFactory
			.getLogger(SubscriberListDAO.class);

	public void updateBounceCounts(final long subListId,
			List<String> emailIdList) {
		String udateQuery = "update SUBSCRIBER_LIST set "
				+ " BOUNCE_COUNT= BOUNCE_COUNT+" + emailIdList.size()
				+ " AND ACTIVE_COUNT= ACTIVE_COUNT-" + emailIdList.size()
				+ " WHERE SUBSCRIBER_LIST_ID =" + subListId;

		logger.debug("Update Bounced Count Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public void updateUnSubscriberCounts(final long subListId,
			List<String> emailIdList) {
		String udateQuery = "update SUBSCRIBER_LIST set "
				+ " UNSUBSCRIBER_COUNT= UNSUBSCRIBER_COUNT+"
				+ emailIdList.size() + " AND ACTIVE_COUNT= ACTIVE_COUNT-"
				+ emailIdList.size() + " WHERE SUBSCRIBER_LIST_ID ="
				+ subListId;

		logger.debug("Update Unsubscriber Count Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public List<SubscriberList> getSubscriberGroups(String orgId) {

		RowMapper<SubscriberList> mapper = new RowMapper<SubscriberList>() {
			public SubscriberList mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SubscriberList subList = new SubscriberList();

				subList.setSubscriberListId(rs.getLong("SUBSCRIBER_LIST_ID"));
				subList.setSubscriberListName(rs
						.getString("SUBSCRIBER_LIST_NAME"));
				subList.setActiveCount(rs.getInt("ACTIVE_COUNT"));
				subList.setBouncedCount(rs.getInt("BOUNCE_COUNT"));
				subList.setUnsubscriberCount(rs.getInt("UNSUBSCRIBER_COUNT"));
				subList.setLastModifiedTime(rs.getString("LAST_MODIFIED_TIME"));

				return subList;
			}
		};

		String selectSubscriberGroupsQuery = "SELECT SL.SUBSCRIBER_LIST_ID,SL.SUBSCRIBER_LIST_NAME,SL.ACTIVE_COUNT,"
				+ " SL.LAST_MODIFIED_TIME,SL.BOUNCE_COUNT,SL.UNSUBSCRIBER_COUNT "
				+ " FROM SUBSCRIBER_LIST SL " + " WHERE SL.ORG_ID=" + orgId;

		logger.debug("Select Subscribers Group Query : "
				+ selectSubscriberGroupsQuery);

		return getJdbcTemplate().query(selectSubscriberGroupsQuery, mapper);
	}

	public long getSubscriberGroupByListName(String listName, long orgId) {

		String checkSubscriberquery = "SELECT SUBSCRIBER_LIST_ID FROM SUBSCRIBER_LIST WHERE SUBSCRIBER_LIST_NAME='"
				+ listName + "' AND ORG_ID =" + orgId;

		logger.debug("Check Subscriber List Query : " + checkSubscriberquery);

		return getJdbcTemplate().queryForLong(checkSubscriberquery);
	}

	public List<SubscriberList> getSubscriberGroup(long subListId) {

		RowMapper<SubscriberList> mapper = new RowMapper<SubscriberList>() {
			public SubscriberList mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SubscriberList subList = new SubscriberList();

				subList.setSubscriberListId(rs.getInt("SUBSCRIBER_LIST_ID"));
				subList.setSubscriberListName(rs
						.getString("SUBSCRIBER_LIST_NAME"));
				subList.setActiveCount(rs.getInt("ACTIVE_COUNT"));
				subList.setBouncedCount(rs.getInt("BOUNCE_COUNT"));
				subList.setUnsubscriberCount(rs.getInt("UNSUBSCRIBER_COUNT"));

				return subList;
			}
		};

		String selectSubscriberQuery = "SELECT SL.SUBSCRIBER_LIST_ID,SL.SUBSCRIBER_LIST_NAME,SL.ACTIVE_COUNT,SL.BOUNCE_COUNT,SL.UNSUBSCRIBER_COUNT "
				+ " FROM SUBSCRIBER_LIST SL "
				+ " WHERE SL.SUBSCRIBER_LIST_ID="
				+ subListId;

		logger.debug("Select Sub group Query : " + selectSubscriberQuery);

		return getJdbcTemplate().query(selectSubscriberQuery, mapper);
	}

	public long addSubGroup(SubscriberList subList) {

		String insertSubGroupQuery = "INSERT INTO SUBSCRIBER_LIST(ORG_ID,SUBSCRIBER_LIST_NAME,USER_ID,CREATED_TIME,LAST_MODIFIED_TIME,ACTIVE_COUNT) "
				+ " VALUES('"
				+ subList.getOrgId()
				+ "','"
				+ subList.getSubscriberListName()
				+ "','"
				+ subList.getUserId()
				+ "','"
				+ subList.getCreatedTime()
				+ "',"
				+ "'"
				+ subList.getLastModifiedTime()
				+ "','"
				+ subList.getActiveCount() + "')";

		logger.debug("Subcriber Group insert Query : " + insertSubGroupQuery);

		getJdbcTemplate().execute(insertSubGroupQuery);

		String selectQuery = "SELECT SUBSCRIBER_LIST_ID FROM SUBSCRIBER_LIST WHERE SUBSCRIBER_LIST_NAME='"
				+ subList.getSubscriberListName()
				+ "' AND ORG_ID ="
				+ subList.getOrgId();

		logger.debug("Select Subscriber Group Query : " + selectQuery);

		return getJdbcTemplate().queryForLong(selectQuery);
	}

	public void addActiveCount(long subscriberListId, int activeCount) {
		String udateQuery = "update SUBSCRIBER_LIST set "
				+ " ACTIVE_COUNT= ACTIVE_COUNT+" + activeCount
				+ " WHERE SUBSCRIBER_LIST_ID =" + subscriberListId;

		logger.debug("Update Active Count Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public void updateActiveCount(long subscriberListId, int activeCount) {
		String udateQuery = "update SUBSCRIBER_LIST set "
				+ " ACTIVE_COUNT= ACTIVE_COUNT-" + activeCount
				+ " WHERE SUBSCRIBER_LIST_ID =" + subscriberListId;

		logger.debug("Update Active Count Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public void updateBouncedCount(long subscriberListId, int bouncedCount) {
		String udateQuery = "update SUBSCRIBER_LIST set "
				+ " BOUNCE_COUNT= BOUNCE_COUNT-" + bouncedCount
				+ " WHERE SUBSCRIBER_LIST_ID =" + subscriberListId;

		logger.debug("Update Bounce Count Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public void updateUnsubscriberCount(long subscriberListId,
			int unsubsriberCount) {
		String udateQuery = "update SUBSCRIBER_LIST set "
				+ " UNSUBSCRIBER_COUNT= UNSUBSCRIBER_COUNT-" + unsubsriberCount
				+ " WHERE SUBSCRIBER_LIST_ID =" + subscriberListId;

		logger.debug("Update Unsubscriber Count Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public void moveSubscribers(long subscriberListId, int count, int fromType,
			int toType) {

		String getFromFiledName = getFieldName(fromType);
		String getToFiledName = getFieldName(toType);

		String moveQuery = "update SUBSCRIBER_LIST SET " + getFromFiledName
				+ " = " + getFromFiledName + "-" + count + "," + getToFiledName
				+ " = " + getToFiledName + "+" + count
				+ " WHERE SUBSCRIBER_LIST_ID=" + subscriberListId;

		System.out.println("Move QUERYYYYYYYYYYYY " + moveQuery);
		logger.debug("Move Subsriber Query : " + moveQuery);

		getJdbcTemplate().execute(moveQuery);
	}

	private String getFieldName(int subType) {
		String toReturn = null;
		if (subType == SubscriberStatusEnum.ACTIVE.getStatus()) {
			toReturn = "ACTIVE_COUNT";
		} else if (subType == SubscriberStatusEnum.BOUNCED.getStatus()) {
			toReturn = "BOUNCE_COUNT";
		} else if (subType == SubscriberStatusEnum.UNSUBSCRIBED.getStatus()) {
			toReturn = "UNSUBSCRIBER_COUNT";
		}

		return toReturn;
	}

	public static SubscriberListDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SubscriberListDAO) ctx.getBean("SubscriberListDAO");
	}

	@Override
	public List<SubscriberReports> getSubscribersByStatus(long campaignId,
			int status) {

		List<SubscriberReports> toReturn = new ArrayList<SubscriberReports>();

		String selectSubscriberStatusQuery = "select sr1.status_id,count(sr.email_id) as count,sr.email_id from subscriber_reports sr,subscriberid_status sr1"
				+ " where campaign_id=? and sr.status=? and sr.subscriber_list_id=sr1.subscriber_list_id and sr.email_id=sr1.email_id group by email_id";

		Object[] parms = { campaignId, status };

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				selectSubscriberStatusQuery, parms);

		for (Map row : rows) {

			SubscriberReports subStatus = new SubscriberReports();

			subStatus.setEmailId((String) row.get("EMAIL_ID"));
			subStatus.setCount(((Long) row.get("count")).intValue());
			subStatus.setSubscriberId((Long) row.get("status_id"));

			System.out.println("ADDINGGGGGGGGGGGG ");

			toReturn.add(subStatus);

		}

		return toReturn;
	}

	@Override
	public void getCampaignReportsBySubscribers(String statusId,
			SubscriberCampaignReportsBean subscribersBean) {

		String selectSubscriberStatusQuery = "select cs.subscriber_list_id,sl.subscriber_list_name,sc.campaign_id,c.campaign_name,cs.sent_time, ss.email_id "
				+ " from subscriber_campaign sc, campaign c, subscriber_list sl, campaign_status cs, subscriberid_status ss "
				+ " where sc.subscriber_id=? and sc.campaign_id=cs.campaign_id and sc.campaign_id=c.campaign_id and cs.subscriber_list_id=sl.subscriber_list_id"
				+ " and ss.status_id=sc.subscriber_id";

		Object[] parms = { statusId };

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				selectSubscriberStatusQuery, parms);

		int sentCount = 0;

		List<SubscriberCampaignBean> campaignsList = new ArrayList<SubscriberCampaignBean>();

		for (Map row : rows) {

			if (sentCount == 0) {
				subscribersBean.setListId((Long) row.get("subscriber_list_id"));
				subscribersBean.setGroupName((String) row
						.get("subscriber_list_name"));
				subscribersBean.setEmailId((String) row.get("email_id"));
			}

			SubscriberCampaignBean bean = new SubscriberCampaignBean();
			//bean.setCampaignDate(df.format((Date) row.get("sent_time")));
			bean.setCampaignName((String) row.get("campaign_name"));
			bean.setCampaignId((Long) row.get("campaign_id"));

			campaignsList.add(bean);

			sentCount = sentCount + 1;

		}
		
		subscribersBean.setCampaignsList(campaignsList);

		subscribersBean.setEmailsSent(sentCount);

	}

	@Override
	public void setCampaignCounts(String statusId,
			SubscriberCampaignReportsBean subscribersBean) {
		String selectSubscriberStatusQuery = "select sr.status,count(sr.status) as count from subscriberid_status ss, subscriber_reports sr "
				+ " where ss.status_id=? and ss.subscriber_list_id=sr.subscriber_list_id and ss.email_id=sr.email_id group by sr.status";

		Object[] parms = { statusId };

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				selectSubscriberStatusQuery, parms);

		for (Map row : rows) {

			int status = (Integer) row.get("status");

			if (status == SubscriberCampaignStatusEnum.OPENED.getStatus()) {
				subscribersBean.setOpened(((Long) row.get("count")).intValue());
			} else if (status == SubscriberCampaignStatusEnum.CLICKED
					.getStatus()) {
				subscribersBean
						.setClicked(((Long) row.get("count")).intValue());
			} /*
			 * else if(status ==
			 * SubscriberCampaignStatusEnum.BOUNCED.getStatus()) {
			 * bean.setBounced(count); } else if(status ==
			 * SubscriberCampaignStatusEnum.UNSUBSCRIBED.getStatus()) {
			 * bean.setUnsubscribed(count); }
			 */

		}

	}
}