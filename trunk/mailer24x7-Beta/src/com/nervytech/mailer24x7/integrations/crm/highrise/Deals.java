package com.nervytech.mailer24x7.integrations.crm.highrise;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duncan
 */
@XmlRootElement(name="deals")
public class Deals implements Serializable, ListWrapper<Deal> {
    
    private static final long serialVersionUID = 1L;    
    
    private List<Deal> deals;

    @Override
    @XmlElement(name="deal")
    public List<Deal> getObjects() {
        return deals;
    }

    @Override
    public void setObjects(List<Deal> objects) {
        this.deals = objects;
    }
    
}
