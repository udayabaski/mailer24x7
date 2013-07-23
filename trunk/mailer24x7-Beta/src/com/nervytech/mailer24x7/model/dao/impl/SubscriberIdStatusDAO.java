package com.nervytech.mailer24x7.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.common.enums.SubscriberStatusEnum;
import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberIdStatusDAO;

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
public class SubscriberIdStatusDAO extends JdbcDaoSupport implements
		ISubscriberIdStatusDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(SubscriberIdStatusDAO.class);

	public List<SubscriberIdStatus> getNextSubscribers(Long subListId,
			Long lastSubcriberId, int status, int limit) {

		RowMapper<SubscriberIdStatus> mapper = new RowMapper<SubscriberIdStatus>() {
			public SubscriberIdStatus mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SubscriberIdStatus subStatus = new SubscriberIdStatus();

				subStatus.setEmailId(rs.getString("EMAIL_ID"));
				subStatus.setStatusId(rs.getLong("STATUS_ID"));

				return subStatus;
			}
		};

		String selectSubscriberQuery = "SELECT * FROM SUBSCRIBERID_STATUS WHERE SUBSCRIBER_LIST_ID="
				+ subListId
				+ " AND STATUS_ID > "
				+ lastSubcriberId
				+ " AND STATUS="
				+ status
				+ " ORDER BY STATUS_ID ASC LIMIT "
				+ limit;

		logger.debug("Subscriber synch Query : " + selectSubscriberQuery);

		return getJdbcTemplate().query(selectSubscriberQuery, mapper);
	}

	public List<SubscriberIdStatus> getSubscribersByLatestCount(
			String subListId, int status, long latestSubscriberSent) {

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubscriberIdStatus subStatus = new SubscriberIdStatus();

				subStatus.setEmailId(rs.getString("EMAIL_ID"));
				subStatus.setStatusId(rs.getLong("STATUS_ID"));

				return subStatus;
			}
		};

		String selectSubscriberQuery = "SELECT * FROM SUBSCRIBERID_STATUS WHERE SUBSCRIBER_LIST_ID="
				+ subListId
				+ " AND STATUS_ID > "
				+ latestSubscriberSent
				+ " AND STATUS=" + status + " ORDER BY STATUS_ID ASC";

		logger.debug("SubscriberId status Query : " + selectSubscriberQuery);

		return getJdbcTemplate().query(selectSubscriberQuery, mapper);
	}

	public void updateSubscriberStatus(long statusId, int status) {
		String udateQuery = "update SUBSCRIBERID_STATUS set status='" + status
				+ "' WHERE STATUS_ID =" + statusId;

		logger.debug("Update Subscriber Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public void addSubscriber(SubscriberIdStatus subStatus) {
		String insertQuery = "INSERT INTO SUBSCRIBERID_STATUS(SUBSCRIBER_LIST_ID,EMAIL_ID,STATUS) "
				+ " VALUES('"
				+ subStatus.getSubscriberListId()
				+ "','"
				+ subStatus.getEmailId() + "','" + subStatus.getStatus() + "')";
		System.out.println("insertQuery ===>>> " + insertQuery);
		getJdbcTemplate().execute(insertQuery);
	}

	public void addBatchSubscriber(final long subscriberListId,
			final long userId, final long orgId, final int status,
			final String[] subscribers) {

		String insertSubscriberQuery = "INSERT INTO SUBSCRIBERID_STATUS(SUBSCRIBER_LIST_ID,EMAIL_ID,STATUS) "
				+ " VALUES(?,?,?)";

		logger.debug("Inser Subscriber Query : " + insertSubscriberQuery);

		getJdbcTemplate().batchUpdate(insertSubscriberQuery,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int count)
							throws SQLException {
						ps.setLong(1, subscriberListId);
						ps.setString(2, subscribers[count]);
						ps.setLong(3, status);
					}

					@Override
					public int getBatchSize() {
						return subscribers.length;
					}
				});

	}

	public void updateSubscriberStatus(final long subListId,
			final List<String> emailIdList, final int status) {
		String sqlStr = "UPDATE SUBSCRIBERID_STATUS SET STATUS = " + status
				+ " WHERE SUBSCRIBER_LIST_ID =" + subListId
				+ " AND EMAIL_ID = ?";

		logger.debug("Update Subscriber Status Query : " + sqlStr);

		getJdbcTemplate().batchUpdate(sqlStr,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, emailIdList.get(i));
					}

					@Override
					public int getBatchSize() {
						return emailIdList.size();
					}
				});
	}

	@Override
	public int getSubscribersCount(String subscriberListId) {
		String getSubscribersCountQuery = "SELECT COUNT(*) FROM SUBSCRIBERID_STATUS WHERE SUBSCRIBER_LIST_ID="
				+ subscriberListId;

		logger.debug("Check Subscriber List Query : "
				+ getSubscribersCountQuery);

		return getJdbcTemplate().queryForInt(getSubscribersCountQuery);

	}
	
	public List<List<SubscriberIdStatus>> getAllSubscribers(long subListId) {

		List<List<SubscriberIdStatus>> allSubscribers = new ArrayList<List<SubscriberIdStatus>>();

		final List<SubscriberIdStatus> activeSubscribers = new ArrayList<SubscriberIdStatus>();
		final List<SubscriberIdStatus> unsubscribedSubscribers = new ArrayList<SubscriberIdStatus>();
		final List<SubscriberIdStatus> bouncedSubscribers = new ArrayList<SubscriberIdStatus>();

		String selectSubscriberQuery = "SELECT * FROM SUBSCRIBERID_STATUS WHERE SUBSCRIBER_LIST_ID="
				+ subListId + " ORDER BY STATUS_ID ASC";

		logger.debug("Select All Subscriber Query : " + selectSubscriberQuery);

		getJdbcTemplate().query(selectSubscriberQuery,
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						SubscriberIdStatus subStatus = new SubscriberIdStatus();

						subStatus.setEmailId(rs.getString("EMAIL_ID"));
						subStatus.setStatusId(rs.getLong("STATUS_ID"));

						if (rs.getInt("STATUS") == SubscriberStatusEnum.ACTIVE
								.getStatus()) {
							activeSubscribers.add(subStatus);
						} else if (rs.getInt("STATUS") == SubscriberStatusEnum.UNSUBSCRIBED
								.getStatus()) {
							unsubscribedSubscribers.add(subStatus);
						} else if (rs.getInt("STATUS") == SubscriberStatusEnum.BOUNCED
								.getStatus()) {
							bouncedSubscribers.add(subStatus);
						}

					}
				});

		allSubscribers.add(activeSubscribers);
		allSubscribers.add(unsubscribedSubscribers);
		allSubscribers.add(bouncedSubscribers);

		return allSubscribers;

	}
	
	public void deleteSubGroup(long subListId) {

		String deleteQuery = "DELETE FROM SUBSCRIBERID_STATUS WHERE SUBSCRIBER_LIST_ID="
				+ subListId;

		logger.debug("Delete Subscrierid status Query : " + deleteQuery);

		getJdbcTemplate().execute(deleteQuery);

		deleteQuery = "DELETE FROM SUBSCRIBER_REPORTS WHERE SUBSCRIBER_LIST_ID="
				+ subListId;

		logger.debug("Delete Subscriber Reports Query : " + deleteQuery);

		getJdbcTemplate().execute(deleteQuery);

		deleteQuery = "DELETE FROM SUBSCRIBER_LIST WHERE SUBSCRIBER_LIST_ID="
				+ subListId;

		logger.debug("Delete Subscriber list Query " + deleteQuery);

		getJdbcTemplate().execute(deleteQuery);
	}

	public void deleteSubscriber(String statusIds) {

		String deleteQuery = "DELETE FROM SUBSCRIBERID_STATUS WHERE STATUS_ID IN ("
				+ statusIds + ")";

		logger.debug("Delete Subscriber Query : " + deleteQuery);

		getJdbcTemplate().execute(deleteQuery);
	}
	
	public void updateSubscribersStatus(String statusIds, int status) {
		String udateQuery = "update SUBSCRIBERID_STATUS set status='" + status
				+ "' WHERE STATUS_ID in (" + statusIds + ")";

		logger.debug("Update Subscribers Status Query : " + udateQuery);

		getJdbcTemplate().execute(udateQuery);
	}

	public static SubscriberIdStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SubscriberIdStatusDAO) ctx.getBean("SubscriberListDAO");
	}

	@Override
	public List<Long> getAllSubscribersByStatus(long subscriberListId,
			int status) {

		final List<Long> activeSubscribers = new ArrayList<Long>();

		String selectSubscriberQuery = "SELECT * FROM SUBSCRIBERID_STATUS WHERE SUBSCRIBER_LIST_ID="
				+ subscriberListId + " and status="+status;

		logger.debug("Select All Subscriber Query : " + selectSubscriberQuery);

		getJdbcTemplate().query(selectSubscriberQuery,
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						activeSubscribers.add(rs.getLong("STATUS_ID"));
					}
				});


		return activeSubscribers;

	}

	@Override
	public void addCampaignSentForSubscribers(final List<Long> subscriebrs,
			final long campaignId) {
		
		String insertSubscriberQuery = "INSERT INTO SUBSCRIBER_CAMPAIGN "
				+ " VALUES(?,?)";

		logger.debug("Inser Subscriber Query : " + insertSubscriberQuery);

		getJdbcTemplate().batchUpdate(insertSubscriberQuery,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int count)
							throws SQLException {
						ps.setLong(1, subscriebrs.get(count));
						ps.setLong(2, campaignId);
					}

					@Override
					public int getBatchSize() {
						return subscriebrs.size();
					}
				});

		
	}

}