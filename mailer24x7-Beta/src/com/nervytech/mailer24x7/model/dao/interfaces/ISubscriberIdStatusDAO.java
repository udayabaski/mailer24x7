/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import java.util.List;

import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;

/**
 * @author bsikkaya
 *
 */
public interface ISubscriberIdStatusDAO {
	
	public List<SubscriberIdStatus> getNextSubscribers(Long subListId,
			Long lastSubcriberId, int status, int limit);
	
	public List<SubscriberIdStatus> getSubscribersByLatestCount(
			String subListId, int status, long latestSubscriberSent);
	
	public void updateSubscriberStatus(final long subListId,
			final List<String> emailIdList, final int status);
}
