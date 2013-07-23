package com.nervytech.mailer24x7.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.common.enums.SubscriberCampaignStatusEnum;
import com.nervytech.mailer24x7.mailgun.CampaignEvent;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberReportsDAO;
import com.nervytech.mailer24x7.reports.bean.BarChartReportsBean;

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
		String sqlStr = "INSERT INTO SUBSCRIBER_REPORTS (CAMPAIGN_ID, SUBSCRIBER_LIST_ID, EMAIL_ID,"
				+ " STATUS, TIME_IN_LONG, CITY, REGION, IP , COUNTRY ) VALUES (?,?,?,?,?,?,?,?,?)";

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

	public Map<Integer, List<Long>> getSubscriberReport(long campaignId,
			int openSatus, int clickStatus) {

		Map<Integer, List<Long>> map = new HashMap<Integer, List<Long>>();

		String selectSubscriberGroupsQuery = "SELECT TIME_IN_LONG,STATUS FROM SUBSCRIBER_REPORTS where CAMPAIGN_ID="
				+ campaignId
				+ " AND STATUS IN ("
				+ openSatus
				+ ","
				+ clickStatus + ")";

		logger.debug("Campaign SubscriberReports Query : "
				+ selectSubscriberGroupsQuery);

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				selectSubscriberGroupsQuery);

		for (Map row : rows) {
			if (map.get((Integer) row.get("STATUS")) == null) {
				List<Long> list = new ArrayList<Long>();
				list.add((Long) row.get("TIME_IN_LONG"));
				map.put((Integer) row.get("STATUS"), list);
			} else {
				List<Long> list = map.get((Integer) row.get("STATUS"));
				list.add((Long) row.get("TIME_IN_LONG"));
				map.put((Integer) row.get("STATUS"), list);
			}
		}

		System.out.println("MAPPPPPPPP " + map);
		return map;
	}

	public static SubscriberReportsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SubscriberReportsDAO) ctx.getBean("SubscriberIdStatusDAO");
	}

	@Override
	public Map<String, BarChartReportsBean> getSubscriberRegionReport(
			long campaignIdLong,int opened, int clicked, int bounced, int unsubscribed) {

		Map<String, BarChartReportsBean> toReturn = new HashMap<String, BarChartReportsBean>();

		String selectSubscriberGroupsQuery = "select status,country,count(country) as count from subscriber_reports where " +
				" campaign_id=? and status in (?,?,?,?) group by status,country";

		Object[] parms = { campaignIdLong, opened, clicked, bounced, unsubscribed };

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				selectSubscriberGroupsQuery,parms);

		for (Map row : rows) {
			
			if(toReturn.get((String)row.get("country")) != null){
				BarChartReportsBean bean = toReturn.get((String)row.get("country"));
				setCountInBean(bean,(Integer) row.get("status"),((Long) row.get("count")).intValue());
			} else {
				BarChartReportsBean bean = new BarChartReportsBean();
				setCountInBean(bean,(Integer) row.get("status"),((Long) row.get("count")).intValue());
				toReturn.put((String)row.get("country"),bean);
			}
			
		}

		return toReturn;
	}

	private void setCountInBean(BarChartReportsBean bean, int status,
			int count) {
		if(status == SubscriberCampaignStatusEnum.OPENED.getStatus()){
			bean.setOpened(count);
		} else if(status == SubscriberCampaignStatusEnum.CLICKED.getStatus()) {
			bean.setClicked(count);
		} else if(status == SubscriberCampaignStatusEnum.BOUNCED.getStatus()) {
			bean.setBounced(count);
		} else if(status == SubscriberCampaignStatusEnum.UNSUBSCRIBED.getStatus()) {
			bean.setUnsubscribed(count);
		}
		
	}
}