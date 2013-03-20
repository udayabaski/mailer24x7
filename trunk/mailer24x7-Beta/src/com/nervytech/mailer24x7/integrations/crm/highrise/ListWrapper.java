package com.nervytech.mailer24x7.integrations.crm.highrise;

import java.util.List;

/**
 * 
 * @author thiagofa
 *
 */
public interface ListWrapper<T> {

	public List<T> getObjects();
	public void setObjects(List<T> objects);
	
}