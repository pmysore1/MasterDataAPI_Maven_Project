/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata;

import com.aol.csopen.sapmasterdata.dao.BasicDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.*;
/**
 *
 * @author pmysore1
 */
public abstract  class OpenCSResultGenerator {
    
    public static String CATALOG = "finance";
    public static String SOURCE = "EDX";
    public static String FRESHNESS = "real-time";
    public static String DATA_TYPE = "master-data";
    public int httpStatusCode=200 ;
   
    public JSONObject getResult(int rowCount, String status, String subCatalog, String message) throws JSONException
    {
        JSONObject jsonResultObject = new JSONObject() ;
        jsonResultObject.put("status", status) ;
        jsonResultObject.put("rows", rowCount) ;
        jsonResultObject.put("message", message) ;
        jsonResultObject.put("catalog", CATALOG) ;
        jsonResultObject.put("subCatalog", subCatalog) ;
        jsonResultObject.put("dataType", DATA_TYPE) ;
        jsonResultObject.put("source", SOURCE) ;
        jsonResultObject.put("freshness", FRESHNESS) ;
        return jsonResultObject ;
    }
    
    public int getHTTPStatusCode() 
    {
        return this.httpStatusCode;
    }
    public void setHTTPStatusCode(int httpStatusCode) 
    {
       this.httpStatusCode = httpStatusCode;
    }
    public String getResponse(String status, String subCatalog, String message) throws JSONException
    {
        return getResponse((List)null, status, subCatalog, message) ;
    }
    
    public String getErrorResponse(String subCatalog, String message) throws JSONException
    {
        return getResponse((List)null, "error", subCatalog, message) ;
    }
     
    public String getResponse(List dataList, String status, String subCatalog, String message) throws JSONException
    {
        JSONObject jsonFinalObject = new JSONObject() ;
        int rowCount = 0 ;
        if(dataList != null)
        {
            jsonFinalObject.put("data", dataList) ;
            rowCount = dataList.size() ;
        }
        jsonFinalObject.put("result", getResult(rowCount,status, subCatalog, message)) ;
        
        jsonFinalObject.put("data", dataList) ;
        return jsonFinalObject.toString() ;
    }
    
    public String getResponse(Map dataMap, String status, String subCatalog, String message) throws JSONException
    {
        JSONObject jsonFinalObject = new JSONObject() ;
        jsonFinalObject.put("result", getResult(1,status, subCatalog, message)) ;
        if(dataMap != null)
            jsonFinalObject.put("data", dataMap) ;
        return jsonFinalObject.toString() ;
    }
    
    public String getFinalResult(String sql, String subCatalog, String errorMessage) throws JSONException
    {
        List data = null ;
        String jsonResponse = null;
        BasicDAO bDao = new BasicDAO();
        List parmsList = new ArrayList();
        Object[] params = parmsList.toArray();
        
        try
        {
            data = bDao.getListData(sql, params);
            if(data.size() == 0)
            {
                jsonResponse = getErrorResponse(subCatalog, errorMessage) ;
                this.httpStatusCode = 404 ;
            }
            else
            {
                jsonResponse = getResponse(data, 
                                                  "success",
                                                  subCatalog,
                                                  "") ;
            }
        }
        catch (SQLException e)
        {
            jsonResponse = getErrorResponse(subCatalog, errorMessage + ", Internal error -" + e.getMessage()) ;
            this.httpStatusCode = 502 ;
        }
         
        return jsonResponse ;
    }
    public String getFinalResult(String sql, String subCatalog, String errorMessage, Map fieldNameMapping) throws JSONException
    {
        List data = null ;
        List newData = null ;
        String jsonResponse = null;
        BasicDAO bDao = new BasicDAO();
        List parmsList = new ArrayList();
        Object[] params = parmsList.toArray();
        
        try
        {
            data = bDao.getListData(sql, params);
            if(data.size() == 0)
            {
                jsonResponse = getErrorResponse(subCatalog, errorMessage) ;
            }
            else
            {
                newData = getModifiedListData(data, fieldNameMapping) ;
                jsonResponse = getResponse(newData, 
                                                  "success",
                                                  subCatalog,
                                                  "") ;
            }
        }
        catch (SQLException e)
        {
            jsonResponse = getErrorResponse(subCatalog, errorMessage + ", Internal error -" + e.getMessage()) ;
        }
         
        return jsonResponse ;
    }
    
    public List getModifiedListData(List inListData, Map fieldMapping)
    {
        List outData = new ArrayList(); ;
        Set set=null;
        Map queryResultMap = null ;
        Map newMap = null ;
        String dbValue ;
        String fieldName ;
        String newFieldName ;
        //Move next key and value of Map by iterator
        Iterator it=null;
        int rowsCount = inListData.size() ;
        System.out.println("No of rows : " + rowsCount) ;
        for(int i = 0 ; i < rowsCount ; i++)
        {
            queryResultMap = (Map) inListData.get(i) ;
            newMap = new HashMap();
            set=fieldMapping.entrySet();
            it=set.iterator();
       
            while(it.hasNext())
            {
                // key=value separator this by Map.Entry to get key and value
                
                Map.Entry m =(Map.Entry)it.next();

                // getKey is used to get key of Map
                fieldName=(String)m.getKey();

                // getValue is used to get value of key in Map
                newFieldName=(String)m.getValue();
                System.out.println("Key :"+fieldName+"  Value :"+newFieldName);
                Object obj = queryResultMap.get(fieldName) ;
                if(obj != null)
                    dbValue = obj.toString() ;
                else 
                    dbValue="";
                newMap.put(newFieldName, dbValue) ;

                //System.out.println("Key :"+fieldName+"  Value :"+newFieldName);
            }
            outData.add(newMap) ;
        }

        return outData ;
    }
    public String stringCheck(String string)
    {
        String result = null ;
        if(string != null)
        {
            if(string.length() > 0)
                result = string ;
                
        }
        return result ;
            
    }
    
}
