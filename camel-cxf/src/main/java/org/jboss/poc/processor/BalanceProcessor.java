package org.jboss.poc.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BalanceProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getIn().setBody("OK PROCESS");
        System.out.println("Passed");
    }
}
