/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
 

/**
 *
 * @author pmysore1
 */
public class SapMDWsException extends WebApplicationException{
       public SapMDWsException()
       {
           super(Response.Status.BAD_REQUEST) ; 
   
       }
       public SapMDWsException(String errorMessage)
       {
            super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).type("text/plain").build()) ; 
   
       }
    
}
