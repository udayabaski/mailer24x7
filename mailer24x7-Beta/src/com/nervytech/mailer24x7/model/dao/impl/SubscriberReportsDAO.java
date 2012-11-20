package com.nervytech.mailer24x7.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.mailgun.CampaignEvent;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberReportsDAO;

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

@Resource(mappedName = "subscriberReportsDAO")
public class SubscriberReportsDAO extends JdbcDaoSupport implements
		ISubscriberReportsDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(SubscriberReportsDAO.class);

	public void addCampaignEvents(final long campaignId,
			final List<CampaignEvent> eventList) {
		String sqlStr = "INSERT INTO SUBSCRIBER_REPORTS (CAMPAIGN_ID, SUBSCRIBER_LIST_ID, EMAIL_ID," +
				" STATUS, TIME_IN_LONG, CITY, REGION, IP , COUNTRY ) VALUES (?,?,?,?,?,?,?,?,?)";

		logger.debug("Subscriber Reports Query : " + sqlStr);

		getJdbcTemplate().batchUpdate(sqlStr,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						CampaignEvent event = eventList.get(i);
						ps.setLong(1, campaignId);
						ps.setLong(2, event.getSubscriberId());
						ps.setString(3, event.getEmail());
						ps.setInt(4, event.getEventStatus());
						ps.setLong(5, event.getEventTime());
						ps.setString(6, event.getCity());
						ps.setString(7, event.getRegion());
						ps.setString(8, event.getIp());
						ps.setString(9, event.getCountry());
					}

					@Override
					public int getBatchSize() {
						return eventList.size();
					}
				});
	}

	public static SubscriberReportsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SubscriberReportsDAO) ctx.getBean("SubscriberIdStatusDAO");
	}
}