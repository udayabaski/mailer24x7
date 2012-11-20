/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import java.util.List;

import com.nervytech.mailer24x7.domain.model.Organization;

/**
 * @author bsikkaya
 * 
 */
public interface IOrganizationDAO {
	
	public List<Organization> getOrganization(String orgName, String country);
	
	public long saveOrganization(Organization org);
	
}
