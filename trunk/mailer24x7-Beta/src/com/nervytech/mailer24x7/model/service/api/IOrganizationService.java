/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import com.nervytech.mailer24x7.domain.model.Organization;

/**
 * @author bsikkaya
 *
 */
public interface IOrganizationService {
	
	public Organization getOrganization(String orgName, String country) ;
	
	public long saveOrganization(Organization org) ;

	public Organization getOrganization(long orgId);

	public void updateOrganization(Organization form);
}
