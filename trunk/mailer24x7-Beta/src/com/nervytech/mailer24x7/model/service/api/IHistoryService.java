/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import java.util.List;

import com.nervytech.mailer24x7.spring.bean.ActivityBean;

/**
 * @author bsikkaya
 *
 */
public interface IHistoryService {
	
	public List<ActivityBean> getLatestActivities(int count,long orgId);
}
