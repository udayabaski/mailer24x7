/**
 * 
 */
package com.nervytech.mailer24x7.common.enums;

/**
 * @author bsikkaya
 *
 */
public enum CampaignTypeEnum {
	IMPORT(0),PLAINTEXT(1),HTMLEDITOR(2),NEWTEMPLATE(3),PREDEFINEDTEMPLATE(4);
   
   int type;
   
   private CampaignTypeEnum(int type) {
	 this.type = type;
   }
   
   public int getType() {
   	return type;
  }
   
}
