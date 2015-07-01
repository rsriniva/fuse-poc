package org.jboss.poc.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RequestResult")
public class RequestResult {
    private final String status;

    public RequestResult(String status) {
        this.status = status;
    }
}
