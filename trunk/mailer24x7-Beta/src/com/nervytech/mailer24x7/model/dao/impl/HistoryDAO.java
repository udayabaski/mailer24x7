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

import com.nervytech.mailer24x7.model.dao.interfaces.IHistoryDAO;
import com.nervytech.mailer24x7.spring.bean.ActivityBean;

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

@Resource(mappedName = "historyDAO")
public class HistoryDAO extends JdbcDaoSupport implements IHistoryDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(HistoryDAO.class);

	@Override
	public List<ActivityBean> getLatestActivities(int count, long orgId) {
		RowMapper<ActivityBean> mapper = new RowMapper<ActivityBean>() {
			public ActivityBean mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ActivityBean activity = new ActivityBean();

				activity.setSubject(rs.getString("SUBJECT"));
				activity.setCreatedBy(rs.getString("CREATED_By"));
				activity.setCreatedDate(rs.getString("CREATED_TIME"));

				return activity;
			}
		};

		String selectCampaignQuery = "SELECT H.HISTORY_ID,H.SUBJECT,H.CREATED_BY,H.CREATED_TIME "
				+ " FROM HISTORY H"
				+ " WHERE H.ORG_ID="
				+ orgId
				+ " ORDER BY H.CREATED_TIME DESC LIMIT " + count;

		logger.debug("Schedule Campaign Query : " + selectCampaignQuery);

		return getJdbcTemplate().query(selectCampaignQuery, mapper);
	}

	public static HistoryDAO getFromApplicationContext(ApplicationContext ctx) {
		return (HistoryDAO) ctx.getBean("UserSessionDAO");
	}

}