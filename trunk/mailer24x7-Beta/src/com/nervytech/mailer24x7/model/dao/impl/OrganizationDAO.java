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

import com.nervytech.mailer24x7.domain.model.Organization;
import com.nervytech.mailer24x7.model.dao.interfaces.IOrganizationDAO;

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

@Resource(mappedName = "organizationDAO")
public class OrganizationDAO extends JdbcDaoSupport implements IOrganizationDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(OrganizationDAO.class);

	public List<Organization> getOrganization(String orgName, String country) {
		RowMapper<Organization> mapper = new RowMapper<Organization>() {
			public Organization mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Organization org = new Organization();
				org.setOrgName(rs.getString("ORG_NAME"));
				org.setContactEmail(rs.getString("CONTACT_EMAIL"));
				return org;
			}
		};
		String orgSQL = "SELECT * FROM ORGANIZATION WHERE ORG_NAME='" + orgName
				+ "' AND COUNTRY='" + country + "'";

		logger.debug("Organization checking Query : " + orgSQL);

		return getJdbcTemplate().query(orgSQL, mapper);
	}

	public long saveOrganization(Organization org) {

		String insertOrgQuery = "INSERT INTO ORGANIZATION (ORG_NAME,DISPLAYNAME,STATUS,CREATED_TIME,CREATED_EMAILID,"
				+ "CONTACT_EMAIL,CONTACT_NO,WEBSITE,ADDRESS,COUNTRY,TIMEZONE,SENDER_EMAIL) "
				+ " VALUES('"
				+ org.getOrgName()
				+ "','"
				+ org.getDisplayName()
				+ "','"
				+ org.getStatus()
				+ "','"
				+ org.getCreatedTime()
				+ "',"
				+ "'"
				+ org.getCreatedEmailId()
				+ "','"
				+ org.getContactEmail()
				+ "','"
				+ org.getContactNo()
				+ "','"
				+ org.getWebsite()
				+ "','"
				+ org.getAddress()
				+ "',"
				+ "'"
				+ org.getCountry()
				+ "','"
				+ org.getTimeZone() + "','" + org.getSenderEmail() + "')";

		logger.debug("Save Oraganization Query : " + insertOrgQuery);

		getJdbcTemplate().execute(insertOrgQuery);

		String selectOrgIdQuery = "SELECT ORG_ID FROM ORGANIZATION"
				+ " WHERE ORG_NAME='" + org.getOrgName() + "' AND "
				+ " COUNTRY='" + org.getCountry() + "'";

		logger.debug("Select Oraganization ID Query : " + selectOrgIdQuery);

		return getJdbcTemplate().queryForLong(selectOrgIdQuery);
	}

	public static OrganizationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OrganizationDAO) ctx.getBean("OrganizationDAO");
	}

	@Override
	public List<Organization> getOrganization(long orgId) {
		RowMapper<Organization> mapper = new RowMapper<Organization>() {
			public Organization mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Organization org = new Organization();
				org.setOrgName(rs.getString("ORG_NAME"));
				org.setContactEmail(rs.getString("CONTACT_EMAIL"));
				org.setDisplayName(rs.getString("DISPLAYNAME"));
				org.setContactNo(rs.getString("CONTACT_NO"));
				org.setWebsite(rs.getString("WEBSITE"));
				org.setCountry(rs.getString("COUNTRY"));
				org.setAddress(rs.getString("ADDRESS"));
				org.setTimeZone(rs.getString("TIMEZONE"));
				org.setSenderEmail(rs.getString("SENDER_EMAIL"));
				return org;
			}
		};
		String orgSQL = "SELECT * FROM ORGANIZATION WHERE ORG_ID="+orgId;

		logger.debug("Organization checking Query : " + orgSQL);

		return getJdbcTemplate().query(orgSQL, mapper);
	}

	@Override
	public void updateOrganization(Organization org) {
		String updateOrgQuery = "UPDATE ORGANIZATION SET DISPLAYNAME='"+org.getDisplayName()+"'," +
				" CONTACT_EMAIL='"+org.getContactEmail()+"',CONTACT_NO='"+org.getContactNo()+"'," +
				" WEBSITE='"+org.getWebsite()+"',ADDRESS='"+org.getWebsite()+"',COUNTRY='"+org.getCountry()+"'," +
				" TIMEZONE='"+org.getTimeZone()+"',SENDER_EMAIL='"+org.getSenderEmail()+"'," +
				" UPDATED_TIME='"+org.getUpdatedTime()+"',UPDATED_EMAILID='"+org.getUpdatedEmailId()+"' " +
						" WHERE ORG_ID="+org.getOrgId();

		logger.debug("Save Oraganization Query : " + updateOrgQuery);

		getJdbcTemplate().execute(updateOrgQuery);
		
	}
}