/**
 * 
 */
package com.nervytech.mailer24x7.common.util;

/**
 * @author bsikkaya
 * 
 */
public class UUIDUtil {

	public static java.util.UUID getTimeUUID() {

		return java.util.UUID.fromString(new com.eaio.uuid.UUID().toString());

	}

	public static java.util.UUID getRandomUUID() {

		return java.util.UUID.randomUUID();

	}
}
