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
@Path("sapvendor")
public class SAPVendorResource extends OpenCSResultGenerator{

    @Context
    private UriInfo context;
    private String SUB_CATALOG_NAME ="sapvendor";
    /* "VENDOR_ID",
   "NAME1",
   "NAME2",
   "NAME3",
   "NAME4",
   "STREET_ADDRESS",
   "CITY",
   "STATE",
   "POSTAL_CODE",
   "COUNTRY",
   "TELEPHONE1",
   "TELEPHONE2",
   "FAX",
   "CENTRAL_BLOCK",
   "PMNTTERMS",
   "UPDATE_DATE",
   "ACTION"*/
     
     private String sapVendorSelectSql = "select VENDOR_ID,"
                                    + "NAME1, "
                                    + "STREET_ADDRESS, "
                                    + "CITY, "
                                    + "STATE,"
                                    + "POSTAL_CODE,"
                                    + "COUNTRY,"
                                    +"TELEPHONE1,"
                                    +"TELEPHONE2,"
                                    +"FAX,"
                                    +"CENTRAL_BLOCK,"
                                    +"PMNTTERMS,"
                                    +"ACTION,"
                                    + "to_char(UPDATE_DATE,'yyyymmdd') UPDATE_DATE"
                                    + " FROM VENDOR_LATEST_SAP_V" ;
     
    /**
     * Creates a new instance of SAPVendorResource
     */
    public SAPVendorResource() {
    }

    /**
     * Retrieves representation of an instance of com.aol.csopen.sapmasterdata.SAPVendorResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
        public Response get(@QueryParam("id") String Id, 
                            @QueryParam("name") String name, 
                            @QueryParam("city") String city, 
                            @QueryParam("phone") String phone,
                            @QueryParam("state") String state,
                            @QueryParam("country") String country,
                            @QueryParam("postalcode") String postalcode
                            ) throws IOException, JSONException{ 
        
        System.out.println("in SAPVendorResource get() ID -- " + Id + ", name =" +  name + ", city = " +city + ", state = " +phone) ;  
        String result = null ;
        
        Id = stringCheck(Id) ;
        name = stringCheck(name) ;
        city = stringCheck(city) ;
        phone = stringCheck(phone) ;
        state = stringCheck(state) ;
        country = stringCheck(country) ;
        postalcode = stringCheck(postalcode) ;
        
        
        if(Id == null && name == null && city == null && phone==null && state==null && country==null && postalcode==null)
           result = super.getErrorResponse(SUB_CATALOG_NAME, 
                                            "Error - At least one query parameter needs to be in the GET URI" ) ; 
        else
        {
            
            result = getSAPVendorRecords(Id, name, city, phone, state,country, postalcode) ;
           
            
        }
        
      
        
        return Response.status(200).entity(result).build();
    }
    public String getSAPVendorRecords(String Id, String name, String city, String phone, String state, String country, String postalcode) throws JSONException
    {
        String sqlWhereClause = getSqlWhereCluase(Id, name, city, phone, state,country, postalcode) ;
        String sqlQuery = sapVendorSelectSql + sqlWhereClause ;
        System.out.println("Where Clause : " + sqlWhereClause) ;
        System.out.println("SQL Query : " + sqlQuery) ;
        
        String jsonResponse = super.getFinalResult(sqlQuery,SUB_CATALOG_NAME, 
                                    "No vendor records found for provided query parameters", getDBColumnToJsonFieldMap()); 
        
        return jsonResponse ;
       
    }
    public Map getDBColumnToJsonFieldMap()
    {
         Map<String,String> mp=new HashMap<>();


        // adding or set elements in Map by put method key and value pair
         
                  
        mp.put("VENDOR_ID", "id");
        mp.put("NAME1", "name");
        mp.put("STREET_ADDRESS", "street_address");
        mp.put("CITY", "city");
        mp.put("STATE", "state");
        mp.put("POSTAL_CODE", "postal_code");
        mp.put("COUNTRY", "country");
        mp.put("TELEPHONE1", "telephone1");
        mp.put("TELEPHONE2", "telephone2");
        mp.put("FAX", "fax"); 
        mp.put("CENTRAL_BLOCK", "central_block"); 
        mp.put("PMNTTERMS", "pmntterms");
        mp.put("ACTION", "action"); 
        mp.put("UPDATE_DATE", "create_date"); 
       

        return mp ;
    }
    public String getSqlWhereCluase(String Id, String name, String city, String phone, String state, String country, String postalcode)
    {
        //String sqlWhereClause = null ;
       String sqlWhereClause = "";
       
       
        
        if (Id != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND "; 
           //sqlWhereClause +=  "upper(VENDOR_ID) like upper('%" + Id+"%')";
           sqlWhereClause +=  "upper(VENDOR_ID) = upper('" + Id+"')";
        }
        if (name != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause +="upper(NAME1)  like upper('%"+ name+"%')";
        }
        if (city != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND " ;
           sqlWhereClause +="upper(CITY) like upper('%"+city+"%')";
        }
        if (phone != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause += "upper(TELEPHONE1) like upper('%" +phone+"%')";
        }
        if (state != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause += "upper(STATE) like upper('%" +state+"%')";
        } 
        if (country != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause += "upper(COUNTRY) like upper('%" +country+"%')";
        }
        if (postalcode != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           //sqlWhereClause += "POSTAL_CODE like '%" +postalcode+"%'";
           sqlWhereClause += "upper(POSTAL_CODE) like upper('%" +postalcode+"%')";
        } 
        return sqlWhereClause;
    }

    /**
     * PUT method for updating or creating an instance of SAPVendorResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
