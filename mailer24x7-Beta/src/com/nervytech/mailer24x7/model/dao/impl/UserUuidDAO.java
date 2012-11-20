package com.nervytech.mailer24x7.model.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.domain.model.UserUuid;
import com.nervytech.mailer24x7.model.dao.interfaces.IUserUuidDAO;

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

@Resource(mappedName = "userUuidDAO")
public class UserUuidDAO extends JdbcDaoSupport implements IUserUuidDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(UserUuidDAO.class);

	public void saveUuid(UserUuid reg) {
		String insertUuidQuery = "INSERT INTO USER_UUID VALUES('"
				+ reg.getUserId() + "','" + reg.getUuid() + "','"
				+ reg.getCreatedTime() + "','" + reg.getType() + "')";

		logger.debug("Uuid query : " + insertUuidQuery);

		getJdbcTemplate().execute(insertUuidQuery);
	}

	public void deleteUuid(String userId) {

		String uuidDeleteQuery = "DELETE FROM USER_UUID WHERE USER_ID='"
				+ userId + "'";

		logger.debug("Delete UUID Query : " + uuidDeleteQuery);

		getJdbcTemplate().execute(uuidDeleteQuery);
	}

	public String getUserId(String confirmationId) {
		String userIdQuery = "SELECT USER_ID FROM USER_UUID WHERE UUID='"
				+ confirmationId + "'";

		logger.debug("Confirmation Id Query : " + userIdQuery);

		return getJdbcTemplate().queryForObject(userIdQuery, String.class);
	}
	
	public String getUuid(String userId) {
		String uuidQuery = "SELECT UUID FROM USER_UUID WHERE USER_ID="
				+ userId;
		
		logger.debug("Get uuid query : "+uuidQuery);
		
		return getJdbcTemplate().queryForObject(uuidQuery, String.class);
	}

	public static UserUuidDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserUuidDAO) ctx.getBean("UserUuidDAO");
	}
}