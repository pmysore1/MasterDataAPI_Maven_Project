/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
/*"record_id":1,
         "rate_type":"monthlyavg",
            "from_currency":"AUD",
            "from_currency_desc":"Australian Dollar",
            "to_currency":"USD",
            "to_currency_desc":"United States Dollar",
            "effective_date":"20140901",
            "exchange_rate":"0.931098696",
            "last_modified":"20140908"*/

@Path("exchangerate")
public class ExchangeRateResource extends OpenCSResultGenerator{

    @Context
    private UriInfo context;
    private String SUB_CATALOG_NAME ="exchangerate" ;
    /*private String exRtSelectSql = "select ROWNUM record_id, EXCHANGE_RATE_TYPE rate_type, "
                                    + "FROM_CURRENCY from_currency, "
                                    + "FROM_CURRENCY_DESC from_currency_desc, "
                                    + "TO_CURRENCY to_currency,"
                                    + "TO_CURRENCY_DESC to_currency_desc,"
                                    + "to_char(DATE_EFFECTIVE_FROM,'yyyymmdd') effective_date,"
                                    + "EXCHANGE_RATE exchange_rate, "
                                    + "to_char(MODIFY_DT,'yyyymmdd') last_modified " +
                                        "from DW_CURRENCY_EXCHANGE_RT_V" ;*/
    private String exRtSelectSql = "select ROWNUM,"
                                    + "EXCHANGE_RATE_TYPE, "
                                    + "FROM_CURRENCY, "
                                    + "FROM_CURRENCY_DESC, "
                                    + "TO_CURRENCY,"
                                    + "TO_CURRENCY_DESC,"
                                    + "to_char(DATE_EFFECTIVE_FROM,'yyyymmdd') DATE_EFFECTIVE_FROM,"
                                    + "EXCHANGE_RATE, "
                                    + "to_char(MODIFY_DT,'yyyymmdd') MODIFY_DT " +
                                        "from DW_CURRENCY_EXCHANGE_RT_V" ;

    /**
     * Creates a new instance of ExchangeRateResource
     */
    public ExchangeRateResource() {
    }

    /**
     * Retrieves representation of an instance of com.aol.csopen.sapmasterdata.ExchangeRateResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
        public Response get(@QueryParam("from") String from, 
                            @QueryParam("to") String to, 
                            @QueryParam("type") String type, 
                            @QueryParam("asofdate") String asofdate) throws IOException,JSONException{ 
        
        System.out.println("in ExchangeRateResource get() from -- " + from + ", to =" +  to + ", type = " +type + ", asofdate = " +asofdate) ;  
        String result = null ;
        if(asofdate == null)
           result = super.getErrorResponse(SUB_CATALOG_NAME, 
                                            "Error - mandatory query param asofdate is not specified in GET URI" ) ; 
        else if(asofdate.length() == 0)
        {
            result = super.getErrorResponse(SUB_CATALOG_NAME, 
                                            "Error - mandatory query param asofdate value is not specified in GET URI" ) ;
           
        }
        else
        {
            
            result = getExchangeRate(from, to, type, asofdate) ;
           
            
        }
        
      
        
        return Response.status(200).entity(result).build();
    }
    public String getExchangeRate(String from, String to, String type, String asofdate) throws JSONException
    {
        String sqlWhereClause = getSqlWhereCluase(from, to, type, asofdate) ;
        String sqlQuery = exRtSelectSql + sqlWhereClause ;
        System.out.println("Where Clause : " + sqlWhereClause) ;
        System.out.println("SQL Query : " + sqlQuery) ;
        
        String jsonResponse = super.getFinalResult(sqlQuery,SUB_CATALOG_NAME, 
                                    "Exchange rate records not found for  -> " + asofdate, getDBColumnToJsonFieldMap()); 
        
        return jsonResponse ;
       
    }
    public Map getDBColumnToJsonFieldMap()
    {
         Map<String,String> mp=new HashMap<>();


        // adding or set elements in Map by put method key and value pair
        mp.put("ROWNUM", "record_id");
        mp.put("EXCHANGE_RATE_TYPE", "rate_type");
        mp.put("FROM_CURRENCY", "from_currency");
        mp.put("FROM_CURRENCY_DESC", "from_currency_desc");
        mp.put("TO_CURRENCY", "to_currency");
        mp.put("TO_CURRENCY_DESC", "to_currency_desc");
        mp.put("DATE_EFFECTIVE_FROM", "effective_date");
        mp.put("EXCHANGE_RATE", "exchange_rate");
        mp.put("MODIFY_DT", "last_modified"); 

        return mp ;
    }
    public String getSqlWhereCluase(String from, String to, String type, String asofdate)
    {
        //String sqlWhereClause = null ;
       String sqlWhereClause = "";
       
        from = stringCheck(from) ;
        to = stringCheck(to) ;
        type = stringCheck(type) ;
        
        if (from != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND "; 
           sqlWhereClause +=  "FROM_CURRENCY = '" + from+"'";
        }
        if (to != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause +="TO_CURRENCY ='"+ to+"'";
        }
        if (type != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND " ;
           sqlWhereClause +="EXCHANGE_RATE_TYPE = '"+type+"'";
        }
        if (asofdate != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause += "DATE_EFFECTIVE_FROM = to_date('" +asofdate+"','yyyymmdd')";
        } 
        return sqlWhereClause;
    }
    
   

    /**
     * PUT method for updating or creating an instance of ExchangeRateResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
