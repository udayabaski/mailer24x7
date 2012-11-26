/**
 * 
 */
package com.nervytech.mailer24x7.model.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nervytech.mailer24x7.model.dao.interfaces.IHistoryDAO;
import com.nervytech.mailer24x7.model.service.api.IHistoryService;
import com.nervytech.mailer24x7.spring.bean.ActivityBean;

/**
 * @author bsikkaya
 *
 */
@Service
public class HistoryService implements IHistoryService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(HistoryService.class);

	@Autowired
	private IHistoryDAO historyDAO;
	
	@Override
	public List<ActivityBean> getLatestActivities(int count, long orgId) {
		List<ActivityBean> activitiesList = null;
		try {
			activitiesList = historyDAO
					.getLatestActivities(count,orgId);
		} catch (Exception e) {
			logger.error("Error while saving user detail.", e);
			throw new RuntimeException(e.getMessage());
		}
		return activitiesList;
	}

}
