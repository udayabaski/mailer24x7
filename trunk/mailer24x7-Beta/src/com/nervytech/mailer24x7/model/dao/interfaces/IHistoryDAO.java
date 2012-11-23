/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import java.util.List;

import com.nervytech.mailer24x7.spring.bean.ActivityBean;

/**
 * @author bsikkaya
 *
 */
public interface IHistoryDAO {

	public List<ActivityBean> getLatestActivities(int count,long orgId);
}
