/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import java.util.List;

/**
 * @author bsikkaya
 *
 */
public interface ISubscriberListDAO {
	
	public void updateBounceCounts(final long subListId,
			List<String> emailIdList);
	
	public void updateUnSubscriberCounts(final long subListId,
			List<String> emailIdList);
}
