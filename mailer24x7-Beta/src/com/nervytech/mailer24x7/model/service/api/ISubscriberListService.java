/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import java.util.List;

/**
 * @author bsikkaya
 *
 */
public interface ISubscriberListService {
	
	public void updateBounceCounts(final long subListId,
			List<String> emailIdList) ;
	
	public void updateUnSubscriberCounts(final long subListId,
			List<String> emailIdList);
}
