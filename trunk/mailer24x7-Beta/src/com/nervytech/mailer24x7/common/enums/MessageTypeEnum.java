/**
 * 
 */
package com.nervytech.mailer24x7.common.enums;

/**
 * @author bsikkaya
 *
 */
public enum MessageTypeEnum {
	INFO(0),SUCCESS(1),WARNING(2),ERROR(3);
   
   int status;
   
   private MessageTypeEnum(int status) {
	 this.status = status;
   }
   
   public int getStatus() {
   	return status;
  }
   
}
