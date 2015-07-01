package org.jboss.poc.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jboss.poc.bean.CustomerTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BalanceHistoryProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        List<CustomerTransaction> ctl = new ArrayList<>();
        ctl.add(new CustomerTransaction(new Date(), 4.5, "Credit Card payment"));
        ctl.add(new CustomerTransaction(new Date(), 20.5, "Credit Card payment"));

        ArrayList wrapperList = new ArrayList();
        wrapperList.add(ctl);
        Object[] wrapperArray = new Object[]{ctl};

        exchange.getOut().setBody(wrapperArray);

        //exchange.getOut().setBody(ctl);
    }
}
