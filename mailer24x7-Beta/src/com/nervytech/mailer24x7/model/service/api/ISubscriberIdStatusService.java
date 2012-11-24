/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import java.util.List;

import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;

/**
 * @author bsikkaya
 * 
 */
public interface ISubscriberIdStatusService {

	public List<SubscriberIdStatus> getNextSubscribers(Long subListId,
			Long lastSubcriberId, int status, int limit);

	public List<SubscriberIdStatus> getSubscribersByLatestCount(
			String subListId, int status, long latestSubscriberSent);

	public void updateSubscriberStatus(final long subListId,
			final List<String> emailIdList, final int status);

	public int getSubscribersCount(String subscriberListId);

	public List<List<SubscriberIdStatus>> getAllSubscribers(long subListId);

	public void addBatchSubscriber(final long subscriberListId,
			final long userId, final long orgId, final int status,
			final String[] subscribers);

	public void deleteSubGroup(long subListId);

	public void deleteSubscriber(String statusIds);
	
	public void updateSubscribersStatus(String statusIds, int status);

}
