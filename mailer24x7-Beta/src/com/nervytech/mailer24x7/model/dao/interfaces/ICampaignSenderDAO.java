/**
 * 
 */
package com.nervytech.mailer24x7.model.dao.interfaces;

import com.nervytech.mailer24x7.domain.model.CampaignSender;

/**
 * @author bsikkaya
 *
 */
public interface ICampaignSenderDAO {
	
	public long getCampaignSenderId(CampaignSender cmpnSender);
	
	public long saveCampaignSender(CampaignSender cmpnSender);

}
