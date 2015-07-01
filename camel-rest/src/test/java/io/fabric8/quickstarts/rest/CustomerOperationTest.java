/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.quickstarts.rest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.*;

public final class CustomerOperationTest {


    public static final String CUSTOMER_OPERATION_ADD = "http://localhost:8181/cxf/opsrv/customerservice/customers/123";
    private static final String CUSTOMER_OPERATION_LIST = "http://localhost:8181/cxf/opsrv/customerservice/customer/operation/123";

    private static final Logger LOG = LoggerFactory.getLogger(CustomerOperationTest.class);
    private URL url;
    private InputStream in;

    /*
     * Just a simple helper method to read bytes from an InputStream and return the String representation.
     */
    private static String getStringFromInputStream(InputStream in) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int c = 0;
        while ((c = in.read()) != -1) {
            bos.write(c);
        }
        in.close();
        bos.close();
        return bos.toString();
    }


    /**
     * HTTP PUT http://localhost:8181/cxf/crm/customerservice/customers is used to upload the contents of
     * the update_customer.xml file to update the customer information for customer 123.
     * <p/>
     * On the server side, it matches the OperationService's updateCustomer() method
     *
     * @throws Exception
     */
    @Test
    public void postCustomerOperationTest() throws IOException {

        LOG.info("Sent HTTP PUT request to update customer info");

        PostMethod post = new PostMethod(CUSTOMER_OPERATION_ADD + "?type=pay&description=test");

        HttpClient httpclient = new HttpClient();
        int result = 0;
        try {
            result = httpclient.executeMethod(post);
            LOG.info("Response status code: " + result);
            LOG.info("Response body: ");
            LOG.info(post.getResponseBodyAsString());
        } catch (IOException e) {
            LOG.error("Error connecting to {}", CUSTOMER_OPERATION_ADD);
            LOG.error("You should build the 'rest' quick start and deploy it to a local Fabric8 before running this test");
            LOG.error("Please read the README.md file in 'rest' quick start root");
            fail("Connection error");
        } finally {
            // Release current connection to the connection pool once you are
            // done
            post.releaseConnection();
        }

        assertEquals(result, 200);
    }

    @Test
       public void getCustomerTest() throws Exception {
           LOG.info("Sent HTTP GET request to query customer info");
           url = new URL(CUSTOMER_OPERATION_LIST);
           InputStream in = null;
           try {
               in = url.openStream();
           } catch (IOException e) {
               LOG.error("Error connecting to {}", CUSTOMER_OPERATION_LIST);
               LOG.error("You should build the 'rest' quick start and deploy it to a local Fabric8 before running this test");
               LOG.error("Please read the README.md file in 'rest' quick start root");
               fail("Connection error");
           }
           String res = getStringFromInputStream(in);
           LOG.info(res);
           assertTrue(res.contains("123"));
       }

}