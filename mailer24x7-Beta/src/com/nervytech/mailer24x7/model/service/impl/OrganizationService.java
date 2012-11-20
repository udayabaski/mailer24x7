/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nervytech.mailer24x7.domain.model.Organization;
import com.nervytech.mailer24x7.model.dao.impl.UserDAO;
import com.nervytech.mailer24x7.model.dao.interfaces.IOrganizationDAO;
import com.nervytech.mailer24x7.model.service.api.IOrganizationService;

/**
 * @author bsikkaya
 * 
 */
public class OrganizationService implements IOrganizationService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	@Autowired
	private IOrganizationDAO orgDAO;

	public Organization getOrganization(String orgName, String country) {
		Organization organization = null;
		try {
			List<Organization> organizations = orgDAO.getOrganization(orgName,country);
			if(organizations != null && organizations.size() > 0)
				organization = organizations.get(0);
		} catch (Exception e) {
			logger.error("Error while retreiving user detail.",e);
			throw new RuntimeException(e.getMessage());
		}
		
		return organization;
	}
	
	public long saveOrganization(Organization org)  {
		long orgId = -1;
		try {
			orgId = orgDAO.saveOrganization(org);
		} catch (Exception e) {
			logger.error("Error while retreiving user detail.",e);
			throw new RuntimeException(e.getMessage());
		}
		return orgId;
		
	}

}
