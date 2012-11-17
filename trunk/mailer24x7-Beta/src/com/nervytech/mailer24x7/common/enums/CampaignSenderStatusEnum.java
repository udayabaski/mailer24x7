/**
 * 
 */
package com.nervytech.mailer24x7.common.enums;

/**
 * @author bsikkaya
 *
 */
public enum CampaignSenderStatusEnum {
   NOTVERIFIED(0),VERIFIED(1);
   
   int status;
   
   private CampaignSenderStatusEnum(int status) {
	 this.status = status;
   }
   
   public int getStatus() {
   	return status;
  }
}
