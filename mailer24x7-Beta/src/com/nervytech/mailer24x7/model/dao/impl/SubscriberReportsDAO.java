package com.nervytech.mailer24x7.model.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

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
public class SubscriberReportsDAO extends JdbcDaoSupport implements ISubscriberReportsDAO{

	private static final Logger logger = LoggerFactory
			.getLogger(SubscriberReportsDAO.class);
	
	public static SubscriberReportsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SubscriberReportsDAO) ctx.getBean("SubscriberIdStatusDAO");
	}
}