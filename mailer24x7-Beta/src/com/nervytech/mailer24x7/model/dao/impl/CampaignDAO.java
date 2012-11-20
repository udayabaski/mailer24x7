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

import com.nervytech.mailer24x7.domain.model.Campaign;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignDAO;

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
public class CampaignDAO extends JdbcDaoSupport implements ICampaignDAO{

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
}