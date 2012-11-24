/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import java.util.List;

import com.nervytech.mailer24x7.domain.model.SubscriberList;

/**
 * @author bsikkaya
 * 
 */
public interface ISubscriberListService {

	public void updateBounceCounts(final long subListId,
			List<String> emailIdList);

	public void updateUnSubscriberCounts(final long subListId,
			List<String> emailIdList);

	public List<SubscriberList> getSubscriberGroups(String orgId);

	public long getSubscriberGroupByListName(String listName, long orgId);

	public long addSubGroup(SubscriberList subList);

	public List<SubscriberList> getSubscriberGroup(long parseLong);

	public void moveSubscribers(long subscriberListId, int length,
			int subscriberType, int moveTo);

	public void updateUnsubscriberCount(long subscriberListId, int length);

	public void updateBouncedCount(long subscriberListId, int length);

	public void updateActiveCount(long subscriberListId, int length);
}
