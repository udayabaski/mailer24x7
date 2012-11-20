/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import java.util.List;

import com.nervytech.mailer24x7.domain.model.Campaign;

/**
 * @author bsikkaya
 *
 */
public interface ICampaignDAO {
	
	public List<Campaign> getCampaignsToSyncBiHoursStats(int status,
			String time, long lowerLimit, long upperLimit);

}
