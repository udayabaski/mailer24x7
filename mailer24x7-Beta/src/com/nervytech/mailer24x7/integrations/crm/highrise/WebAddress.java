package com.nervytech.mailer24x7.integrations.crm.highrise;

import java.io.Serializable;

/**
 *
 * @author duncan
 * 
 */
public class WebAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String url;
    private String location;

    public WebAddress() {
    }

    public WebAddress(String number, String location) {
        this.url = number;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String number) {
        this.url = number;
    }

    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            WebAddress other = (WebAddress) obj;
            if (id == null) {
                    if (other.id != null)
                            return false;
            } else if (!id.equals(other.id))
                    return false;
            return true;
    }
    
}
