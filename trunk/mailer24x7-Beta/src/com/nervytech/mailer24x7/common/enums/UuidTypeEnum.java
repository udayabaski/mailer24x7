/**
 * 
 */
package com.nervytech.mailer24x7.common.enums;

/**
 * @author bsikkaya
 *
 */
public enum UuidTypeEnum {
   CONFIRM(0),RESET(1);
   
   int status;
   
   private UuidTypeEnum(int status) {
	 this.status = status;
   }
   
   public int getStatus() {
   	return status;
  }
}
