package com.nervytech.mailer24x7.model.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.domain.model.CampaignSender;
import com.nervytech.mailer24x7.model.dao.interfaces.ICampaignSenderDAO;

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

@Resource(mappedName = "campaignSenderDAO")
public class CampaignSenderDAO extends JdbcDaoSupport implements ICampaignSenderDAO{

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignSenderDAO.class);
	
	public long getCampaignSenderId(CampaignSender cmpnSender) {

		String selectCmpnSenderQuery = "SELECT SENDER_ID FROM CAMPAIGN_SENDER WHERE ORG_ID='"
				+ cmpnSender.getOrgId()
				+ "' AND DISPLAY_NAME='"
				+ cmpnSender.getDisplayName()
				+ "' AND EMAIL_ID='"
				+ cmpnSender.getEmailId() + "'";

		logger.debug("Select CampaignSender Query : " + selectCmpnSenderQuery);

		return getJdbcTemplate().queryForLong(selectCmpnSenderQuery);
	}

	public long saveCampaignSender(CampaignSender cmpnSender) {
		String insertQuery = "INSERT INTO CAMPAIGN_SENDER (ORG_ID,DISPLAY_NAME,EMAIL_ID,STATUS) "
				+ " VALUES('"
				+ cmpnSender.getOrgId()
				+ "','"
				+ cmpnSender.getDisplayName()
				+ "','"
				+ cmpnSender.getEmailId()
				+ "','" + cmpnSender.getStatus() + "')";

		logger.debug("Save Campaign Sender Query : " + insertQuery);

		getJdbcTemplate().execute(insertQuery);

		String selectCmpnSenderQuery = "SELECT SENDER_ID FROM CAMPAIGN_SENDER WHERE ORG_ID='"
				+ cmpnSender.getOrgId()
				+ "' AND DISPLAY_NAME='"
				+ cmpnSender.getDisplayName()
				+ "' AND EMAIL_ID='"
				+ cmpnSender.getEmailId() + "'";

		logger.debug("Select CampaignSender Query : " + selectCmpnSenderQuery);

		return getJdbcTemplate().queryForLong(selectCmpnSenderQuery);
	}

	public static CampaignSenderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CampaignSenderDAO) ctx.getBean("CampaignSenderDAO");
	}
}