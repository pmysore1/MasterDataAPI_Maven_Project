/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata;

import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.JSONException;

/**
 * REST Web Service
 *
 * @author pmysore1
 */
@Path("profitcenter")
public class ProfitcenterResource extends OpenCSResultGenerator{

    @Context
    private UriInfo context;
    private String SUB_CATALOG_NAME ="profitcenter" ;

    /**
     * Creates a new instance of ProfitcenterResource
     */
    public ProfitcenterResource() {
    }

    /**
     * Retrieves representation of an instance of com.aol.csopen.sapmasterdata.ProfitcenterResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    //public Response getList(@Context UriInfo ui) throws IOException,JSONException{ 
    //public Response get(@QueryParam("companycode") String companyCode, @QueryParam("projid") String projID) throws IOException,JSONException{
        public Response get(@QueryParam("controlarea") String controlArea, @QueryParam("profitcenter") String profitCenter, @QueryParam("personincharge") String personInChage) throws IOException,JSONException{ 
        
        System.out.println("in ProfitcenterResource get() --" ) ;  
        String result = null ;
        if(controlArea != null)
        {
            System.out.println("In ProfitcenterResource get() -- query params specified. controlarea : " + controlArea) ; 
            if(controlArea.equals(""))
            {
                System.out.println("Error- in ProfitcenterResource get() -- query param is specified. But controlarea value is blank");
                result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param controlarea value is blank" ) ;
            }
            else if(profitCenter != null)
            {
                result = getProfitCenterDetails(profitCenter,controlArea) ;
                //result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param both controlarea and wbsid can't be specified" ) ;
            }
            else 
                result = getProfitCenterList(controlArea, personInChage) ;
        }
        else // if (wbsElement != null)
        {
            if(profitCenter != null)
                result = getProfitCenterDetails(profitCenter) ;
            else
            result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param controlarea is not specified" ) ;
           
        }
        
        return Response.status(200).entity(result).build();
    }
    public String getProfitCenterList(String controllingArea, String personIncharge) throws IOException, JSONException
    {
        System.out.println("getProfitCenterList: t****** ");
        String jsonResponse ;
        // create list data 
        String whereClause = " FROM PROFIT_CENTER where CONTROL_AREA ='"+ controllingArea +"'";
        
        if(personIncharge != null)
        {
            if(personIncharge.length()!= 0)
                whereClause += "AND CONTACT_NAME='" + personIncharge +"'";
            
        }
        //String sql = "select * from WBS_SAP where WBS_ELEMENT like '" + personIncharge + "%'";
        String sql = "select CONTROL_AREA as CO_AREA , PROFIT_CENTER as PROFIT_CTR , VALID_TO_DATE as VALID_TO, DESCRIPTION as PCTR_NAME ,DESCRIPTION as LONG_TEXT, CONTACT_NAME as IN_CHARGE_USER, DEPARTMENT, LOCK_IND" + whereClause ;
        //if(personIncharge.equals(""))
          //  jsonResponse = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param personIncharge is specified but it is blank" ) ;
        
        jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for controlling area=" + controllingArea + ", personIncharge=" + personIncharge);   
      
        return jsonResponse ;
    }  
    
   
    public String getProfitCenterDetails(String profitCetner) throws IOException, JSONException
    {
         System.out.println("getProfitCenterList: t****** profitCetner :" +profitCetner );

        // create list data 
        
        String jsonResponse = null ;
  
        if(profitCetner.length()!= 0)
        {
            String sql = "select CONTROL_AREA as CO_AREA , PROFIT_CENTER as PROFIT_CTR , VALID_TO_DATE as VALID_TO, DESCRIPTION as PCTR_NAME ,DESCRIPTION as LONG_TEXT, CONTACT_NAME as IN_CHARGE_USER, DEPARTMENT, LOCK_IND from PROFIT_CENTER where PROFIT_CENTER='" + profitCetner +"'" ;
            jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found profitcenter=" + profitCetner); 

        }
        else
            jsonResponse = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param profitcenter is blank" ) ;
 
        return jsonResponse;

    }
    
    public String getProfitCenterDetails(String profitCetner, String controlArea) throws IOException, JSONException
    {
         System.out.println("getProfitCenterList: t****** profitCetner :" +profitCetner );

        // create list data 
        
        String jsonResponse = null ;
  
        if(profitCetner.length()!= 0)
        {
            String sql = "select CONTROL_AREA as CO_AREA , PROFIT_CENTER as PROFIT_CTR , VALID_TO_DATE as VALID_TO, DESCRIPTION as PCTR_NAME ,DESCRIPTION as LONG_TEXT, CONTACT_NAME as IN_CHARGE_USER, DEPARTMENT, LOCK_IND from PROFIT_CENTER where PROFIT_CENTER='" + profitCetner +"' AND CONTROL_AREA ='" +controlArea + "'";
            jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found profitcenter=" + profitCetner); 

        }
        else
            jsonResponse = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param profitcenter is blank" ) ;
 
        return jsonResponse;

    }

    /**
     * PUT method for updating or creating an instance of ProfitcenterResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
