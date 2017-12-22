/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author pmysore1
 */
@Path("p2pcompany")
public class P2PComapny {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of P2PComapny
     */
    public P2PComapny() {
    }

    /**
     * Retrieves representation of an instance of com.aol.csopen.sapmasterdata.P2PComapny
     * @param companyid
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{companyid}")
    @Produces("application/json")
    public String getJson(@PathParam("companyid") String companyid) {
        //TODO return proper representation object
        
        System.out.println("comanyID: " +companyid ) ;
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of P2PComapny
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
