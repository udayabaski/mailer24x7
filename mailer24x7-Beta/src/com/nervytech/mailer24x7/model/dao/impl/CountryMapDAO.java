package com.nervytech.mailer24x7.model.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nervytech.mailer24x7.model.dao.interfaces.ICountryMapDAO;

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

@Resource(mappedName = "countryMapDAO")
public class CountryMapDAO extends JdbcDaoSupport implements ICountryMapDAO{

	private static final Logger logger = LoggerFactory
			.getLogger(CountryMapDAO.class);
	
	public static CountryMapDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CountryMapDAO) ctx.getBean("CountryMapDAO");
	}
}