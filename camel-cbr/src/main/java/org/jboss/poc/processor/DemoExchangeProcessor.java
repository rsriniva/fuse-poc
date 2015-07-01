package org.jboss.poc.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DemoExchangeProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {

        String message = (String) exchange.getIn().getBody();

        if (message.contains("ERROR")) {
            throw new Exception("We send it to error");
        }

        if (message.contains("INVOICE")) {
            exchange.getIn().setHeader("TYPE", "INVOICE");
        }

        if (message.contains("MESSAGE")) {
            exchange.getIn().setHeader("TYPE", "MESSAGE");
        }
    }
}
