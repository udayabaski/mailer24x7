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

import com.nervytech.mailer24x7.common.enums.UserRoleEnum;
import com.nervytech.mailer24x7.common.enums.UserStatusEnum;
import com.nervytech.mailer24x7.domain.model.User;
import com.nervytech.mailer24x7.model.dao.interfaces.IUserDAO;

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

@Resource(mappedName = "userDAO")
public class UserDAO extends JdbcDaoSupport implements IUserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	public List<User> getUserByEmailId(String emailId) {

		RowMapper<User> mapper = new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User usr = new User();
				usr.setUserId(rs.getInt("USER_ID"));
				usr.setOrgId(rs.getInt("ORG_ID"));
				usr.setEmailId(rs.getString("EMAIL_ID"));
				usr.setPassword(rs.getString("PASSWORD"));
				usr.setRole(rs.getInt("ROLE"));
				usr.setStatus(rs.getInt("STATUS"));
				usr.setFullName(rs.getString("FULL_NAME"));

				return usr;
			}
		};
		String selectUserQuery = "SELECT * FROM USER WHERE EMAIL_ID='"
				+ emailId + "'";

		logger.debug("Select user Query : " + selectUserQuery);

		return getJdbcTemplate().query(selectUserQuery, mapper);
	}

	public List<User> getUserByUserId(long userId) {

		RowMapper<User> mapper = new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User usr = new User();
				usr.setUserId(rs.getInt("USER_ID"));
				usr.setOrgId(rs.getInt("ORG_ID"));
				usr.setEmailId(rs.getString("EMAIL_ID"));
				usr.setPassword(rs.getString("PASSWORD"));
				usr.setDisplayName(rs.getString("DISPLAYNAME"));
				usr.setRole(rs.getInt("ROLE"));
				usr.setStatus(rs.getInt("STATUS"));
				usr.setContactNo(rs.getString("CONTACT_NO"));
				usr.setFullName(rs.getString("FULL_NAME"));
				usr.setLanguage(rs.getString("LANGUAGE"));
				usr.setTimeZone(rs.getString("TIMEZONE"));

				return usr;
			}
		};

		String userSQL = "SELECT * FROM USER WHERE USER_ID=" + userId;

		logger.debug("Get User Query : " + userSQL);

		return getJdbcTemplate().query(userSQL, mapper);
	}

	public long saveUser(User user) {

		String saveUserQuery = "INSERT INTO USER (ORG_ID,EMAIL_ID,PASSWORD,DISPLAYNAME,ROLE,STATUS,CONTACT_NO,FULL_NAME,LANGUAGE,TIMEZONE,CREATED_TIME) "
				+ " VALUES('"
				+ user.getOrgId()
				+ "','"
				+ user.getEmailId()
				+ "','"
				+ user.getPassword()
				+ "','"
				+ user.getDisplayName()
				+ "',"
				+ "'"
				+ user.getRole()
				+ "','"
				+ user.getStatus()
				+ "','"
				+ user.getContactNo()
				+ "','"
				+ user.getFullName()
				+ "',"
				+ "'"
				+ user.getLanguage()
				+ "','"
				+ user.getTimeZone()
				+ "','"
				+ user.getCreatedTime() + "')";

		logger.debug("Save user Query : " + saveUserQuery);

		getJdbcTemplate().execute(saveUserQuery);

		String selectUserIdQuery = "SELECT USER_ID FROM USER WHERE EMAIL_ID='"
				+ user.getEmailId() + "'";

		logger.debug("Select UserId Query : " + selectUserIdQuery);

		return getJdbcTemplate().queryForLong(selectUserIdQuery);
	}

	public void updateEncodedPassword(User user) {
		String updateQuery = "UPDATE USER SET PASSWORD='" + user.getPassword()
				+ "' where EMAIL_ID='" + user.getEmailId() + "'";

		logger.debug("Password encoded for the user : " + user.getEmailId());

		getJdbcTemplate().execute(updateQuery);
	}

	public void enableUser(String userId) {

		String userEnableQuery = "UPDATE USER SET STATUS='"
				+ UserStatusEnum.ENABLED.getStatus() + "' where USER_ID='"
				+ userId + "'";

		logger.debug("User enable Query : " + userEnableQuery);

		getJdbcTemplate().execute(userEnableQuery);
	}

	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}

	@Override
	public void updateUser(User usr) {
		String updateQuery = "UPDATE USER SET DISPLAYNAME='"
				+ usr.getDisplayName() + "', CONTACT_NO='" + usr.getContactNo()
				+ "', FULL_NAME='" + usr.getFullName() + "'," + " LANGUAGE='"
				+ usr.getLanguage() + "', TIMEZONE='" + usr.getTimeZone()
				+ "', UPDATED_TIME='" + usr.getUpdatedTime() + "'"
				+ " where USER_ID=" + usr.getUserId();

		getJdbcTemplate().execute(updateQuery);
	}

	public List<User> getUsers(String orgId) {

		RowMapper<User> mapper = new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User usr = new User();
				usr.setUserId(rs.getInt("USER_ID"));
				usr.setOrgId(rs.getInt("ORG_ID"));
				usr.setEmailId(rs.getString("EMAIL_ID"));
				usr.setRole(rs.getInt("ROLE"));
				usr.setFullName(rs.getString("FULL_NAME"));
				usr.setDisplayName(rs.getString("DISPLAYNAME"));
				usr.setRole(rs.getInt("ROLE"));

				return usr;
			}
		};
		String userSQL = "SELECT * FROM USER WHERE ORG_ID='" + orgId + "'";

		logger.debug("Select Users Query : " + userSQL);

		return getJdbcTemplate().query(userSQL, mapper);
	}
}