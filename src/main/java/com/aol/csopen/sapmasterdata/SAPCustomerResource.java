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
@Path("sapcustomers")
public class SAPCustomerResource extends OpenCSResultGenerator{

    @Context
    private UriInfo context;
    private String SUB_CATALOG_NAME ="sapcustomer";
    
    private String sapCustomerSelectSql = "select c.SAP_CUSTOMER_ID,"
                                    + "c.NAME1,"
                                    + "c.NAME2,"
                                    + "c.NAME3,"
                                    + "c.NAME4,"
                                    + "c.PARTY_TYPE,"
                                    + "c.STREET_NAME1, "
                                    + "c.STREET_NAME2, "
                                    + "c.CITY, "
                                    + "c.STATE,"
                                    + "c.POSTAL_CODE,"
                                    + "c.COUNTRY,"
                                    +"c.CUSTOMER_EMAIL,"
                                    +"c.TELEPHONE_LANDLINE,"
                                    +"c.TELEPHONE_EXT,"
                                    +"c.TELEPHONE_MOBILE,"
                                    +"c.FAX,"
                                    +"c.CENTRAL_BLOCK,"
                                    +"c.ADVERTISER_FLAG,"
                                    +"c.AGENCY_FLAG,"
                                    +"c.PARENT_BP_NUMBER,"
                                    +"c.VAT_ID,"
                                    +"c.PMNTTERMS,"
                                    +"c.SEARCH_TERM1,"
                                    +"c.SEARCH_TERM2,"
                                    +"c.STANDARD_COMM_METHOD,"
                                    +"c.ACTION,"
                                    +"c.PO_BOX_ADDRESS,"
                                    + "to_char(c.UPDATE_DATE,'yyyymmdd') UPDATE_DATE"
                                    + " FROM CUSTOMER_SAP c" ;
                                    
    
     /*select * from customer_sap c 
    inner join
    (select sap_customer_id, MAX(update_date) as recent_date from customer_sap group by sap_customer_id) c2
    on c.sap_customer_id = c2.sap_customer_id
    and  c.update_date =c2.recent_date
    and c.name1='SPORTSBET'*/
            
    /**
     * Creates a new instance of SAPCustomerResource
     */
    public SAPCustomerResource() {
    }

    /**
     * Retrieves representation of an instance of com.aol.csopen.sapmasterdata.SAPCustomerResource
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
        
        System.out.println("in SAPCustomerResource get() ID -- " + Id + ", name =" +  name + ", city = " +city + ", state = " +phone) ;  
        String result = null ;
        Id = stringCheck(Id) ;
        name = stringCheck(name) ;
        city = stringCheck(city) ;
        phone = stringCheck(phone) ;
        state = stringCheck(state) ;
        country = stringCheck(country) ;
        postalcode = stringCheck(postalcode) ;
        
        
        if(Id == null && name == null && city == null && phone==null && state==null && country==null && postalcode==null)
        {
           result = super.getErrorResponse(SUB_CATALOG_NAME, 
                                          "Error - At least one query parameter needs to be in the GET URI" ) ; 
           super.setHTTPStatusCode(400);
        }
        else
        {
            
            result = getSAPCustomerRecords(Id, name, city, phone, state,country, postalcode) ;
           
            
        }
        System.out.println("Returning HTTP status code : " + super.getHTTPStatusCode());
        return Response.status(super.getHTTPStatusCode()).entity(result).build();
    }
    public String getSAPCustomerRecords(String Id, String name, String city, String phone, String state, String country, String postalcode) throws JSONException
    {
        String sqlWhereClause = getSqlWhereCluase(Id, name, city, phone, state,country, postalcode) ;
        String sqlQuery = sapCustomerSelectSql + sqlWhereClause ;
        System.out.println("Where Clause : " + sqlWhereClause) ;
        System.out.println("SQL Query : " + sqlQuery) ;
        
        String jsonResponse = super.getFinalResult(sqlQuery,SUB_CATALOG_NAME, 
                                    "No customer records found for provided query parameters", getDBColumnToJsonFieldMap()); 
        
        return jsonResponse ;
       
    }
    public Map getDBColumnToJsonFieldMap()
    {
         Map<String,String> mp=new HashMap<>();


        // adding or set elements in Map by put method key and value pair
         
                  
        mp.put("SAP_CUSTOMER_ID", "id");
        mp.put("NAME1", "name1");
        mp.put("NAME2", "name2");
        mp.put("NAME3", "name3");
        mp.put("NAME4", "name4");
        mp.put("STREET_NAME1", "stretAddress1");
        mp.put("STREET_NAME2", "streetAddress2");
        mp.put("CITY", "city");
        mp.put("STATE", "state");
        mp.put("POSTAL_CODE", "postalCode");
        mp.put("COUNTRY", "country");
        mp.put("CUSTOMER_EMAIL", "email");
        mp.put("TELEPHONE_LANDLINE", "telephoneLandline");
        mp.put("TELEPHONE_EXT", "telephoneExt");
        mp.put("TELEPHONE_MOBILE", "telephoneMobile");
        mp.put("FAX", "fax"); 
        mp.put("CENTRAL_BLOCK", "centralBlock");
        mp.put("ADVERTISER_FLAG", "advertiserFlag"); 
        mp.put("AGENCY_FLAG", "agencyFlag"); 
        mp.put("PARENT_BP_NUMBER", "parentBPNumber"); 
        mp.put("VAT_ID", "vatID"); 
        mp.put("PMNTTERMS", "pmntterms"); 
        mp.put("SEARCH_TERM1", "searchTerm1");
        mp.put("SEARCH_TERM2", "searchTerm2"); 
        mp.put("STANDARD_COMM_METHOD", "communicationMethod");
        mp.put("ACTION", "action"); 
        mp.put("UPDATE_DATE", "createDate");
        mp.put("PO_BOX_ADDRESS", "poBoxAddress"); 
        
       

        return mp ;
    }
    public String getSqlWhereCluase(String Id, String name, String city, String phone, String state, String country, String postalcode)
    {
        //String sqlWhereClause = null ;
       String sqlWhereClause = " INNER JOIN "
                                    + " (select sap_customer_id, MAX(update_date) as recent_date from customer_sap group by sap_customer_id) c2"
                                    + " on c.sap_customer_id = c2.sap_customer_id" 
                                    + " and  c.update_date = c2.recent_date" ;
       
      
        
        if (Id != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND "; 
           sqlWhereClause +=  "upper(c.SAP_CUSTOMER_ID) = upper('" + Id+"')";
        }
        if (name != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause +="upper(c.NAME1)  like upper('%"+ name+"%')";
        }
        if (city != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND " ;
           sqlWhereClause +="upper(c.CITY) like upper('%"+city+"%')";
        }
        if (phone != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause += "upper(c.TELEPHONE_LANDLINE) like upper('%" +phone+"%')";
        }
        if (state != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause += "upper(c.STATE) like upper('%" +state+"%')";
        } 
        if (country != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           sqlWhereClause += "upper(c.COUNTRY) like upper('%" +country+"%')";
        }
        if (postalcode != null) {
           sqlWhereClause += (sqlWhereClause.equals("")) ? " WHERE " : " AND ";
           //sqlWhereClause += "POSTAL_CODE = '%" +postalcode+"%'";
           sqlWhereClause += "upper(c.POSTAL_CODE) like upper('%" +postalcode+"%')";
        } 
        return sqlWhereClause;
    }
    /**
     * PUT method for updating or creating an instance of SAPCustomerResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
