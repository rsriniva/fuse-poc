package org.jboss.poc.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CustomerOperation")
public class CustomerOperation {
    private String type = "";
    private String description = "";

    public CustomerOperation() {

    }

    public CustomerOperation(String type, String description) {
        this.setType(type);
        this.setDescription(description);

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
