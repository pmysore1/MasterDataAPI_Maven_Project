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
@Path("glaccount")
public class GlaccountResource extends OpenCSResultGenerator{

    @Context
    private UriInfo context;
    private String SUB_CATALOG_NAME ="glaccount" ;

    /**
     * Creates a new instance of GlaccountResource
     */
    public GlaccountResource() {
    }

    /**
     * Retrieves representation of an instance of com.aol.csopen.sapmasterdata.GlaccountResource
     * @return an instance of java.lang.String
     */
    
    @GET
    @Produces("application/json")
    //public Response getList(@Context UriInfo ui) throws IOException,JSONException{ 
    public Response getDetails(@QueryParam("companycode") String companyCode, @QueryParam("glaccount") String GLAccount) throws IOException,JSONException{ 
            System.out.println("in GlaccountResource -- getDetails, companyCode="+ companyCode + ",glaccount=" +GLAccount ) ;  
        String result = null ;
        if(companyCode != null)
        {
            System.out.println("In GlaccountResource getDetails() -- query params specified. companycode : " + companyCode) ; 
            if(companyCode.equals(""))
            {
                System.out.println("Error- in GlaccountResource getDetails() -- query param is specified. But copmanycode value is blank");
                result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query param copmanycode value is blank" ) ;
            }
            else if (GLAccount != null)
            {
                System.out.println("GLAccount not NULL");
                if(!GLAccount.equals(""))
                    result = getGLAccountDetails(companyCode, GLAccount);
                else
                    result = getGLAccountList(companyCode) ;
            }
            else
                result = getGLAccountList(companyCode) ;
        }
        else
        {
            if (GLAccount != null)
                result = getGLAccountDetails(GLAccount);
            else
            {
                System.out.println("in GlaccountResource get() -- query params companycode or gl account not specified") ;
                result = super.getErrorResponse(SUB_CATALOG_NAME, "Error - query params either companycode or glaccount not specified in GET URI" ) ;
            }
        }
        
        //MultivaluedMap<String, String> pathParams = ui.getPathParameters(); 
        
        return Response.status(200).entity(result).build();
    }
    
    public String getGLAccountDetails(String companyCode, String GLAccountNo) throws IOException, JSONException
    {
         System.out.println("getGLAccountDetails: t****** ");

        // create list data 
       //Outputs: COMP_CODE, GL_ACCOUNT, SHORT_TEXT, LONG_TEXT
        //String sql = "select COMP_CD, ACCT_NUMBER, SHORT_NAME as SHORT_TEXT, SHORT_NAME as LONG_TEXT,CHRT_ACCTS,BAL_SH_ACC,INC_ST_ACT,ACCT_CURR,ACCT_CURR_ISO,TAX_CODE,NO_TAX_REQUIRED from GL_NUMBER where COMP_CD='" + companyCode + "' AND ACCT_NUMBER='" + GLAccountNo + "'";
         
         String sql = "select COMP_CD, ACCT_NUMBER, LOCK_IND, SHORT_NAME as SHORT_TEXT, SHORT_NAME as LONG_TEXT,CHRT_ACCTS,DELETION_FLAG from GL_NUMBER where COMP_CD='" + companyCode + "' AND ACCT_NUMBER='" + GLAccountNo + "'";
        //  Map<String, String> data = bDao.getOneData( sql, params);
        String jsonResponse ;
       

        jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for companyCode =" + companyCode + ", GL Account=" +GLAccountNo );   
      
        return jsonResponse ;
    } 
    
    public String getGLAccountDetails(String GLAccountNo) throws IOException, JSONException
    {
         System.out.println("getGLAccountDetails: t****** ");

        // create list data 
       //Outputs: COMP_CODE, GL_ACCOUNT, SHORT_TEXT, LONG_TEXT
        //String sql = "select COMP_CD, ACCT_NUMBER, SHORT_NAME as SHORT_TEXT, SHORT_NAME as LONG_TEXT,CHRT_ACCTS,BAL_SH_ACC,INC_ST_ACT,ACCT_CURR,ACCT_CURR_ISO,TAX_CODE,NO_TAX_REQUIRED from GL_NUMBER where COMP_CD='" + companyCode + "' AND ACCT_NUMBER='" + GLAccountNo + "'";
         
        String sql = "select COMP_CD, ACCT_NUMBER, LOCK_IND, SHORT_NAME as SHORT_TEXT, SHORT_NAME as LONG_TEXT,CHRT_ACCTS,DELETION_FLAG from GL_NUMBER where ACCT_NUMBER='" + GLAccountNo + "'";
        //  Map<String, String> data = bDao.getOneData( sql, params);
        String jsonResponse ;
  
        jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for GL Account=" +GLAccountNo );   
      
        return jsonResponse ;
    }  
    public String getGLAccountList(String companyCode) throws IOException, JSONException
    {
         System.out.println("getGLAccountList: t****** ");

        // create list data 
        
        String jsonResponse = null ;
        
        //String sql = "select COMPANY_CD as COMP_CODE,ACCT_NUMBER as GL_ACCOUNT, GL_ACCOUNT_NAME from GL_NUMBER where COMPANYCODE='" + companyCode + "'" ;
        String sql = "select COMP_CD, ACCT_NUMBER, SHORT_NAME as SHORT_TEXT, SHORT_NAME as LONG_TEXT, LOCK_IND, DELETION_FLAG from GL_NUMBER where COMP_CD='" + companyCode + "'" ;
       // COMP_CODE, GL_ACCOUNT, SHORT_TEXT, LONG_TEXT
                
              
        //String sql = "select OBJ_NUM, WBS_ELEMENT, WBS_DESC, PARENT_WBS, COMP_CD, PROJ_ID, SYS_STATUS from WBS_SAP where " +whereClause ;
        //  Map<String, String> data = bDao.getOneData( sql, params);
        
        jsonResponse = super.getFinalResult(sql,SUB_CATALOG_NAME, "Records not found for companycode=" + companyCode); 
        
                                                    
        return jsonResponse;

    }

    /**
     * PUT method for updating or creating an instance of GlaccountResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
