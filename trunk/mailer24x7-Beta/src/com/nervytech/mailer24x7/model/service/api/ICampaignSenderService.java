/**
 * 
 */
package com.nervytech.mailer24x7.model.service.api;

import com.nervytech.mailer24x7.domain.model.CampaignSender;

/**
 * @author bsikkaya
 *
 */
public interface ICampaignSenderService {
	
	public long getCampaignSenderId(CampaignSender cmpnSender);
	
	public long saveCampaignSender(CampaignSender cmpnSender);

}
