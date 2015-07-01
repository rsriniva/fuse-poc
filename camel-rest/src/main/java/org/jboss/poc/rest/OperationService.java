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
package org.jboss.poc.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This Java class with be hosted in the URI path defined by the @Path annotation. @Path annotations on the methods
 * of this class always refer to a path relative to the path defined at the class level.
 * <p/>
 * For example, with 'http://localhost:8181/cxf' as the default CXF servlet path and '/crm' as the JAX-RS server path,
 * this class will be hosted in 'http://localhost:8181/cxf/crm/customerservice'.  An @Path("/customers") annotation on
 * one of the methods would result in 'http://localhost:8181/cxf/crm/customerservice/customers'.
 */
@Path("/customerservice/")
@Api(value = "/customerservice", description = "Operations about customerservice")
public class OperationService {

    private static final Logger LOG = LoggerFactory.getLogger(OperationService.class);


    private MessageContext jaxrsContext;
    private Map<String, List<CustomerOperation>> customer_operations = new HashMap<String, List<CustomerOperation>>();
    private List<CustomerOperation> operations = new ArrayList<>();

    public OperationService() {

    }

    @POST
    @Path("/customers/{id}/")
    @Produces("application/xml")
    @Consumes({"application/xml", "application/json"})
    @ApiOperation(value = "Find Customer by ID", notes = "More notes about this method", response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Invalid ID supplied"),
            @ApiResponse(code = 204, message = "Customer not found")
    })
    public Response addOperation(@ApiParam(value = "ID of Customer to fetch", required = true)
                                 @PathParam("id")
                                 String id,
                                 @ApiParam(value = "Type of operation", required = true)
                                 @QueryParam("type")
                                 String type,
                                 @ApiParam(value = "Description of operation", required = true)
                                 @QueryParam("description")
                                 String description
    ) {
        LOG.info("Adding operation getCustomer, Customer id is [{}] operation type is [{}] description is [{}]", id, type , description);

        if (!customer_operations.containsKey(id)) {
            customer_operations.put(id, new ArrayList<CustomerOperation>());
        }

        customer_operations.get(id).add(new CustomerOperation(type, description));

        return Response.ok().build();
    }


    @GET
    @Path("/customer/operation/{id}/")
    @Produces("application/xml")
    @ApiOperation(value = "Find Customer operations", notes = "More notes about this method", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Invalid ID supplied"),
            @ApiResponse(code = 204, message = "Customer not found")
    })
    public List<CustomerOperation> getCustomerOperations(@ApiParam(value = "ID of Customer to fetch", required = true) @PathParam("id") String id) {
        LOG.info("Invoking getCustomer, Customer id is: {}", id);
        return customer_operations.get(id);
    }


    @Context
    public void setMessageContext(MessageContext messageContext) {
        this.jaxrsContext = messageContext;
    }

}