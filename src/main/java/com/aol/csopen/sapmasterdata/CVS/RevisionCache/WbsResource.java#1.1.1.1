/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata;

import com.aol.csopen.sapmasterdata.dao.BasicDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.UriInfo;
import org.json.JSONException;

/**
 * REST Web Service
 *
 * @author pmysore1
 */
@Path("wbs")
public class WbsResource extends OpenCSResultGenerator{

    @Context
    private UriInfo context;
    private String SUB_CATALOG_NAME ="wbs" ;

    /**
     * Creates a new instance of WbsResource
     */
    public WbsResource() {
    }

    
    @GET
    @Produces("application/json")
    //public Response getList(@Context UriInfo ui) throws IOException,JSONException{ 
    //public Response get(@QueryParam("companycode") String companyCode, @QueryParam("projid") String projID) throws IOException,JSONException{
        public Response get(@QueryParam("companycode") String companyCode, @QueryParam("projid") String projID, @QueryParam("wbsid") String wbsElement) throws IOException,JSONException{ 
        
        System.out.println("in WBSResource get() -- " + companyCode + ", projid =" +  projID + "wbsid = " +wbsElement ) ;  
        String result = null ;
        if(companyCode == null && projID == null && wbsElement == null)
           result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - none of the query params specified in GET URI" ) ; 
        else if(companyCode != null)
        {
            System.out.println("In WbsResource get() -- query params specified. companycode : " + companyCode) ;
            result = getWBSListByCompany(companyCode, wbsElement, projID) ;
           
            
        }
        else if(wbsElement != null )
        {
            System.out.println("In WbsResource get() -- query params specified. wbsElement : " + wbsElement) ;
            result = getWBSListByWBSElement(companyCode, wbsElement, projID) ;
           
            
        }
        else if(projID != null )
        {
            System.out.println("In WbsResource get() -- query params specified. projID : " + projID) ;
            result = getWBSListByProjectID(companyCode, wbsElement, projID) ;
           
            
        }
      
        
        return Response.status(200).entity(result).build();
    }
        
    /*
         @GET
    @Produces("application/json")
    //public Response getList(@Context UriInfo ui) throws IOException,JSONException{ 
    //public Response get(@QueryParam("companycode") String companyCode, @QueryParam("projid") String projID) throws IOException,JSONException{
        public Response get(@QueryParam("companycode") String companyCode, @QueryParam("projid") String projID, @QueryParam("wbsid") String wbsElement) throws IOException,JSONException{ 
        
        System.out.println("in WBSResource get() -- getList" ) ;  
        String result = null ;
        if(companyCode != null)
        {
            System.out.println("In WbsResource get() -- query params specified. companycode : " + companyCode) ;
            
            if(companyCode.equals(""))
            {
                System.out.println("Error- in WbsResource get() -- query param is specified. But copmanycode value is blank");
                result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param copmanycode value is blank" ) ;
            }
            else if(wbsElement != null)
            {
                result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param both copmanycode and wbsid can't be specified" ) ;
            }
            else
                result = getWBSList(companyCode, projID) ;
        }
        else if(wbsElement == null)
        {
            result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param wbsid or companycode not specified in GET URI" ) ;
        }
        else // if (wbsElement != null)
        {
            if(wbsElement.equals(""))
                result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param wbsid is blank" ) ;
            else
                result = result = getWBSDetails(wbsElement) ;
        }
        
        
        return Response.status(200).entity(result).build();
    }
     */
    /*@GET
    @Path("{wbs}")
    @Produces("application/json")
    public Response get(@Context UriInfo ui) throws IOException,JSONException{ 
   // public Response get(@QueryParam("companycode") String companyCode, @QueryParam("projid") String projID) throws IOException,JSONException{ 
        //MultivaluedMap<String, String> queryParams = ui.getQueryParameters(); 
        MultivaluedMap<String, String> pathParams = ui.getPathParameters() ;
     
        String result = null ;
        
        //List<String> companyCode = null ;
        
        String wbsElement = null;
     
        wbsElement = pathParams.getFirst("wbs") ;
        if(wbsElement == null )
        {
            System.out.println("in WBSResource get() -- wbs is not specified. returning error" ) ;  
            result = result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - Invalid URI. Path param wbs is not specified" ) ; 
        }
        else
        {
           
            System.out.println("in WBSResource get() -- path param wbs element specified - " + wbsElement) ;
            result = getWBSDetails(wbsElement) ;               
        }
        return Response.status(200).entity(result).build();
    }*/
    public String getWBSDetails(String wbsElement) throws IOException, JSONException
    {
         System.out.println("getWBSDetails: t****** ");

        // create list data 
     
           
        String sql = "select SYS_STATUS, OBJ_NUM,PROJ_ID,PARENT_WBS,WBS_ELEMENT, WBS_DESC, CREATE_DT from WBS_SAP where WBS_ELEMENT like '" + wbsElement + "%'";
        //  Map<String, String> data = bDao.getOneData( sql, params);
        String jsonResponse ;

        jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for WBS -> " + wbsElement);   
      
        return jsonResponse ;
    }  
    public String getWBSList(String companyCode, String projID) throws IOException, JSONException
    {
         System.out.println("getWBSList: t****** ");

        // create list data 
        
        String jsonResponse = null ;
        
        String whereClause = "COMP_CD ='"+ companyCode +"'";
        
        if(projID != null)
        {
            if(projID.length()!= 0)
                whereClause += "AND PROJ_ID='" + projID +"'";
        }
                
              
        String sql = "select OBJ_NUM, WBS_ELEMENT, WBS_DESC, PARENT_WBS, COMP_CD, PROJ_ID, SYS_STATUS from WBS_SAP where " +whereClause ;
        //  Map<String, String> data = bDao.getOneData( sql, params);
        System.out.println("getWBSList: t****** SQL : " + sql);
        jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for companycode=" + companyCode +", projid=" + projID); 
        
                                                    
        return jsonResponse;

    }
    public String getWBSListByCompany(String companyCode, String wbsID, String projID) throws IOException, JSONException
    {
         System.out.println("getWBSListByCompany: t****** ");

        // create list data 
        
        String jsonResponse = null ;
        String whereClause = null;
        
        if(companyCode.length()!= 0)
        {
           
            whereClause = "COMP_CD ='"+ companyCode +"'";
            if(projID != null)
            {
                if(projID.length()!= 0)
                    whereClause += " AND PROJ_ID='" + projID +"'";
            }
            if(wbsID != null)
            {
                if(wbsID.length()!= 0)
                    whereClause += " AND WBS_ELEMENT='" + wbsID +"'";
            }
        }
        else if(projID != null)
        {
            if(projID.length()!= 0)
            {
                whereClause = "PROJ_ID ='"+ projID +"'";
                if(wbsID != null)
                {
                    if(wbsID.length()!= 0)
                        whereClause += " AND WBS_ELEMENT='" + wbsID +"'";
                }
            }
            else if(wbsID != null)
            {
                if(wbsID.length()!= 0)
                    whereClause = "WBS_ELEMENT ='"+ wbsID +"'";
            }
        }
        else if(wbsID != null)
        {
            if(wbsID.length()!= 0)
                whereClause = "WBS_ELEMENT ='"+ wbsID +"'";
        }
        
        if(whereClause != null)
        {
            //String sql = "select OBJ_NUM, WBS_ELEMENT, WBS_DESC, PARENT_WBS, COMP_CD, PROJ_ID, SYS_STATUS from WBS_SAP where " +whereClause ;
            String sql = "select SYS_STATUS, OBJ_NUM,PROJ_ID,PARENT_WBS,WBS_ELEMENT, WBS_DESC, CREATE_DT from WBS_SAP where " +whereClause ;
            //  Map<String, String> data = bDao.getOneData( sql, params);
            System.out.println("getWBSList: t****** SQL : " + sql);
            jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for companycode=" + companyCode +", projid=" + projID + ", wbsid = " + wbsID); 
        }
        else
            jsonResponse = super.getErrorResponse(SUB_CATALOG_NAME, "Error - none of the query params specified in GET URI" ) ;
        
                                                    
        return jsonResponse;

    }
    public String getWBSListByWBSElement(String companyCode, String wbsID, String projID) throws IOException, JSONException
    {
         System.out.println("getWBSList: t****** ");

        // create list data 
        
        String jsonResponse = null ;
        String whereClause = null;
        
        if(wbsID.length()!= 0)
        {
           
            whereClause = "WBS_ELEMENT ='"+ wbsID +"'";
            if(projID != null)
            {
                if(projID.length()!= 0)
                    whereClause += "AND PROJ_ID='" + projID +"'";
            }
            if(companyCode != null)
            {
                if(companyCode.length()!= 0)
                    whereClause += "AND COMP_CD='" + companyCode +"'";
            }
        }
        else if(projID != null)
        {
            if(projID.length()!= 0)
            {
                whereClause = "PROJ_ID ='"+ projID +"'";
                if(wbsID != null)
                {
                    if(wbsID.length()!= 0)
                        whereClause += "AND WBS_ELEMENT='" + wbsID +"'";
                }
            }
            else if(companyCode != null)
            {
                if(companyCode.length()!= 0)
                    whereClause = "COMP_CD ='"+ companyCode +"'";
            }
        }
        else if(companyCode != null)
        {
            if(companyCode.length()!= 0)
                whereClause = "COMP_CD ='"+ companyCode +"'";
        }
 
        if(whereClause != null)
        {
            //String sql = "select OBJ_NUM, WBS_ELEMENT, WBS_DESC, PARENT_WBS, COMP_CD, PROJ_ID, SYS_STATUS from WBS_SAP where " +whereClause ;
            String sql = "select SYS_STATUS, OBJ_NUM,PROJ_ID,PARENT_WBS,WBS_ELEMENT, WBS_DESC, CREATE_DT from WBS_SAP where " +whereClause ;
            //  Map<String, String> data = bDao.getOneData( sql, params);
            System.out.println("getWBSList: t****** SQL : " + sql);
            jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for companycode=" + companyCode +", projid=" + projID); 
        }
        else
            jsonResponse = super.getErrorResponse(SUB_CATALOG_NAME, "Error - none of the query params specified in GET URI" ) ;
        
                                                    
        return jsonResponse;

    }
    public String getWBSListByProjectID(String companyCode, String wbsID, String projID) throws IOException, JSONException
    {
         System.out.println("getWBSList: t****** ");

        // create list data 
        
        String jsonResponse = null ;
        String whereClause = null;
        
        if(projID.length()!= 0)
        {
           
            whereClause = "PROJ_ID ='"+ projID +"'";
            if(wbsID != null)
            {
                if(wbsID.length()!= 0)
                    whereClause += "AND WBS_ELEMENT='" + wbsID +"'";
            }
            if(companyCode != null)
            {
                if(companyCode.length()!= 0)
                    whereClause += "AND COMP_CD='" + companyCode +"'";
            }
        }
        else if(wbsID != null)
        {
            if(wbsID.length()!= 0)
            {
                whereClause = "WBS_ELEMENT ='"+ projID +"'";
                if(companyCode != null)
                {
                    if(companyCode.length()!= 0)
                        whereClause += "AND COMP_CD='" + wbsID +"'";
                }
            }
            else if(companyCode != null)
            {
                if(companyCode.length()!= 0)
                    whereClause = "COMP_CD ='"+ companyCode +"'";
            }
        }
        else if(companyCode != null)
        {
            if(companyCode.length()!= 0)
                whereClause = "COMP_CD ='"+ companyCode +"'";
        }
   
        if(whereClause != null)
        {
            //String sql = "select OBJ_NUM, WBS_ELEMENT, WBS_DESC, PARENT_WBS, COMP_CD, PROJ_ID, SYS_STATUS from WBS_SAP where " +whereClause ;
            String sql = "select SYS_STATUS, OBJ_NUM,PROJ_ID,PARENT_WBS,WBS_ELEMENT, WBS_DESC, CREATE_DT from WBS_SAP where " +whereClause ;
            //  Map<String, String> data = bDao.getOneData( sql, params);
            System.out.println("getWBSList: t****** SQL : " + sql);
            jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for companycode=" + companyCode +", projid=" + projID); 
        }
        else
            jsonResponse = super.getErrorResponse(SUB_CATALOG_NAME, "Error - none of the query params specified in GET URI" ) ;
        
                                                    
        return jsonResponse;

    }
    /**
     * PUT method for updating or creating an instance of WbsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}

/*
 @GET
    @Produces("application/json")
    //public Response get(@Context UriInfo ui) throws IOException,JSONException{ 
    public Response get(@QueryParam("companycode") String companyCode, @QueryParam("projid") String projID) throws IOException,JSONException{ 
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters(); 
     
        String result = null ;
        
        //List<String> companyCode = null ;
        String companyCode = null;
        String wbsElement = null;
        String projID = null ;
        
        if(queryParams.isEmpty())
        {
          System.out.println("in WBSResource get() -- No query params. returning error" ) ;  
          result = result = super.getErrorResponse("wbs", "Error - Mandatory query param companycode or wbs is not specified" ) ; ;
        }
        else
        {
            companyCode = queryParams.getFirst("companycode") ;
            wbsElement = queryParams.getFirst("wbs") ;
            projID      = queryParams.getFirst("projid") ;
            if(companyCode != null)
            {
                System.out.println("In WbsResource get() -- query params specified. companycode : " + companyCode) ; 
                if(companyCode.equals(""))
                {
                    System.out.println("Error- in WbsResource get() -- query param is specified. But copmanycode value is blank");
                    result = super.getErrorResponse("wbs", "Error - query param copmanycode value is blank" ) ;
                }
                else
                    result = getWBSList(companyCode, projID) ;
            }
            else if(wbsElement != null)
            {
                System.out.println("in WBSResource get() -- query params wbs element specified - " + wbsElement) ;
                result = getWBSDetails(wbsElement) ;
                result = super.getErrorResponse("wbs", "Error - query param companycode not specified in GET URI" ) ;
                       
            }
            else
            {
                System.out.println("in WbsResource get() -- query params companycode is not specified") ;
                result = super.getErrorResponse("wbs", "Error - query param companycode not specified in GET URI" ) ;
            }
        }
*/