/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nervytech.mailer24x7.domain.model.SubscriberIdStatus;
import com.nervytech.mailer24x7.model.dao.interfaces.ISubscriberIdStatusDAO;
import com.nervytech.mailer24x7.model.service.api.ISubscriberIdStatusService;

/**
 * @author bsikkaya
 * 
 */
public class SubscriberIdStatusService implements ISubscriberIdStatusService {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignStatusService.class);

	@Autowired
	private ISubscriberIdStatusDAO subscriberIdStatusDAO;

	@Override
	@Transactional
	public List<SubscriberIdStatus> getNextSubscribers(Long subListId,
			Long lastSubcriberId, int status, int limit) {
		List<SubscriberIdStatus> list = null;
		try {
			list = subscriberIdStatusDAO.getNextSubscribers(subListId,
					lastSubcriberId, status, limit);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	@Transactional
	public List<SubscriberIdStatus> getSubscribersByLatestCount(
			String subListId, int status, long latestSubscriberSent) {
		List<SubscriberIdStatus> list = null;
		try {
			list = subscriberIdStatusDAO.getSubscribersByLatestCount(subListId,
					status, latestSubscriberSent);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	public void updateSubscriberStatus(long subListId,
			List<String> emailIdList, int status) {
		try {
			subscriberIdStatusDAO.updateSubscriberStatus(subListId,
					emailIdList, status);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}		
	}
}
