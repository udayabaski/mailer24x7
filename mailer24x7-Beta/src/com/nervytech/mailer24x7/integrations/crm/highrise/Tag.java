package com.nervytech.mailer24x7.integrations.crm.highrise;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author duncan
 */
@XmlRootElement(name="name")
public class Tag {
    
    private String tag;
    private String partyId;

    public Tag() {
    }

    public Tag(String tag, String partyId) {
        this.tag = tag;
        this.partyId = partyId;
    }

    @XmlValue
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @XmlTransient
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
    
}
