/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata;

import com.aol.csopen.sapmasterdata.dao.BasicDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.*;

/**
 * REST Web Service
 *
 * @author pmysore1
 */
@Path("company")
public class CompanyResource extends OpenCSResultGenerator {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CompanyResource
     */
    public CompanyResource() {
    }

    /**
     * Retrieves representation of an instance of com.aol.csopen.sapmasterdata.CompanyResource
     * @return an instance of java.lang.String
     */
   /*@GET
   @Produces("application/json")
   
    public String getList() {
        System.out.println("in ComnyResource getList") ;
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Produces("application/json")
    
    public String getCompanyDetails(@QueryParam("companycode") String companycode) {
    
        System.out.println("in ComnyResource getCompanyDetails -- Companycode :" +companycode ) ;
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }*/

    @GET
    @Produces("application/json")
    public Response get(@Context UriInfo ui) throws IOException,JSONException{ 
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters(); 
     
        String result = null ;
        
        //List<String> companyCode = null ;
        String companyCode = null;
        if(queryParams.isEmpty())
        {
          System.out.println("in ComnyResource get() -- No query params. So need to return all company codes" ) ;  
          result = getCompanyList() ;
        }
        else
        {
            companyCode = queryParams.getFirst("companycode") ;
            if(companyCode != null)
            {
                System.out.println("In ComnyResource get() -- query params specified. companycode : " + companyCode) ; 
                if(companyCode.equals(""))
                {
                    System.out.println("Error- in ComnyResource get() -- query param is specified. But copmanycode value is blank");
                    result = super.getErrorResponse("company", "Error - query param is specified. But copmanycode value is blank" ) ;
                }
                else
                    result = getCompanyDetails(companyCode) ;
            }
            else
            {
                System.out.println("in ComnyResource get() -- query params companycode is not specified") ;
                //result = super.getErrorResponse("company", "Error - query param companycode not specified in GET URI" ) ;
                result = getCompanyList() ;
                       
            }
        }
        //MultivaluedMap<String, String> pathParams = ui.getPathParameters(); 
        
        return Response.status(200).entity(result).build();

         //throw new UnsupportedOperationException();
    }
    
    public String getCompanyDetails(String companyCode) throws IOException, JSONException
    {
         System.out.println("getCompanyList: t****** ");

        // create list data 
       
        //String sql = "select * from P2P_COMPANY where COMPANY_CODE like '" + companyCode + "%'";
        String sql = "select * from company where MODIFY_DATE in (SELECT MAX(MODIFY_DATE) from company group by COMP_CD) and comp_cd='" +companyCode +"'" ;
        //  Map<String, String> data = bDao.getOneData( sql, params);
        String jsonResponse ;
        jsonResponse = super.getFinalResult(sql,"company", "Record detatails not found for " + companyCode); 
        
        return jsonResponse ;
    }  
    public String getCompanyList() throws IOException, JSONException
    {
         System.out.println("getCompanyList: t****** ");

        // create list data 
        
        //String sql = "select COMPANY_CODE as COMPANY,DESCR as NAME  from P2P_COMPANY";
         
         String sql = "select * from company where MODIFY_DATE in (SELECT MAX(MODIFY_DATE) from company group by COMP_CD)" ;
        //  Map<String, String> data = bDao.getOneData( sql, params);
        
     
        String jsonResponse = super.getFinalResult(sql,"company", "Comapny records not found");
                                            
        return jsonResponse;

    }
    public String getJSONResponse(List data,
                                  String dataJson, 
                                  String status,
                                  int rowCount,
                                  String message,
                                  String catalog,
                                  String subCatalog,
                                  String dataType,
                                  String source,
                                  String freshness) throws JSONException
    {
        String jsonResponse = null ;
        JSONObject jsonResultObject = new JSONObject() ;
        JSONArray jsonArray = new JSONArray() ;
        JSONObject jsonFinalObject = new JSONObject() ;
        jsonResultObject.put("status", status) ;
        jsonResultObject.put("rows", rowCount) ;
        jsonResultObject.put("message", message) ;
        jsonResultObject.put("catalog", catalog) ;
        jsonResultObject.put("sub_catalog", subCatalog) ;
        jsonResultObject.put("data_type", dataType) ;
        jsonResultObject.put("source", source) ;
        jsonResultObject.put("freshness", freshness) ;
        jsonFinalObject.put("result", jsonResultObject) ;
        //jsonResultObject.
        jsonFinalObject.put("data", data) ;
        return jsonFinalObject.toString() ;
        //jsonArray.put(new JSONObject().put("result", jsonResultObject);
    }
   /* public String getCompanyListByCompanyCode(String companyCode)
    {
        
    }*/
    /*public String getResultJson(String status, int recordCount, int message, String catalog, String dataType, String source, )
    {
        String jsonResult = null;
        recordCount = data.size() ;
    }*/
  /*  public setErrorResonse()
    {
        
    }*/
    /**
     * PUT method for updating or creating an instance of CompanyResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
