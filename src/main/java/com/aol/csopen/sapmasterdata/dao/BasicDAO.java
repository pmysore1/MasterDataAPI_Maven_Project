/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aol.csopen.sapmasterdata.dao;

import com.aol.csopen.sapmasterdata.SapMDWsException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author afitzwater1
 */
public class BasicDAO {

    protected Context context = null;
    private static final Logger logger = LogManager.getLogger();
    public static String csFinDS = "jdbc/edx_fin";

    static DataSource ds = null;
    private String providerUrl = null;
    protected Map dsMap = new HashMap(); // contains datasource keyed by dsname

    /**
     * Lookup and initialize the datasource
     */
    protected DataSource getDS() throws SapMDWsException {
        return getDS(csFinDS);
    }

    protected DataSource getDS(String dsName) throws SapMDWsException {

        logger.debug("Looking up Data source : " + dsName);

        DataSource dataSource = (DataSource) dsMap.get(dsName);
        logger.trace("dataSource is : " + dataSource);

        if (dataSource == null) {
            try {

                Hashtable ht = new Hashtable();
                //ht.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
                ht.put(Context.INITIAL_CONTEXT_FACTORY,
                        "org.apache.naming.java.javaURLContextFactory");
                if (getProviderUrl() != null) {
                    ht.put(Context.PROVIDER_URL, getProviderUrl());
                }
                Context initCtx = new InitialContext(ht);
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                logger.trace("looking up dsName: " + dsName);

                dataSource = (DataSource) envCtx.lookup(dsName);
                logger.trace("data source " + dataSource);

                // and add to map
                dsMap.put(dsName, dataSource);

            } catch (NamingException e) {
                e.printStackTrace();
                throw new SapMDWsException("DAO Naming Exception: "
                        + e.toString());
            }
        }
        return dataSource;
    }
    
    /*
    public static DataSource getDataSource() {
        logger.debug("dataSource is: " + ds);
        if (ds != null) {
            logger.debug("data source is not null, returning ds: " + ds);
            return ds;
        }
        try {
            Context context = new InitialContext();
            logger.debug(" lookup dataSource : " + csOpenDS);
            ds = (DataSource) context.lookup(csOpenDS);
            logger.debug("after lookup dataSource is: " + ds);

        } catch (Exception e) {
            System.out.println("Error obtain data source");
        }
        return ds;
    }*/

    /**
     * @return the providerUrl
     */
    public String getProviderUrl() {
        return providerUrl;
    }

    /**
     * @param providerUrl the providerUrl to set
     */
    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }

    // generic int query
    public boolean checkCountQuery(String lookupQuery, String queryParm)
            throws SapMDWsException {
        int cnt = -1;
        String methodName = "checkCountQuery(" + queryParm + ")";
        logger.debug(methodName);

        logger.trace(methodName + ":query:" + lookupQuery);
        try {
            DataSource dataSource = getDS();
            // logger.trace(methodName + ":dataSource:" + dataSource);
            // Create a QueryRunner that will use connections from
            // the given DataSource

            QueryRunner run = new QueryRunner(dataSource);

            logger.trace(methodName + ":QueryRunner:" + run);
            cnt = run.query(lookupQuery, intHandler, queryParm);

            logger.trace("validate count is: " + cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cnt > 0) {
            return true;
        }
        return false;
    }
    ////////////////////////////////////////////////////////////
    // generic handlers
    /**
     * *
     * returns single object resulting from query. map keys will be column names
     * (String) and map values will be column values (String). Returns null if
     * no object/data found
     *
     * @param sql
     * @param parmList
     * @return
     * @throws OtcWsException
     */
    public Map<String, String> getOneData(String sql, Object[] params) throws SapMDWsException {
        Map<String, String> data = null;
        String methodName = "getData(" + sql + ")";
        logger.debug(methodName);

        try {
            DataSource dataSource = getDS();
            logger.debug(methodName + ":dataSource:" + dataSource);
            // Create a QueryRunner that will use connections from
            // the given DataSource

            QueryRunner run = new QueryRunner(dataSource);

            logger.debug(methodName + ":QueryRunner:" + run);
            data = run.query(sql, params, oneResultHandler);

            logger.debug("getData data is: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public List getListData(String sql, Object[] params) throws SQLException {
       // init list to null, will return null if no data found
        List data = null;
 
        // debugs/tracking
        String methodName = "getListData(" + "" + ")";
        String methodNameLong = "getListData(" + sql + ")";
        logger.debug(methodName);

        try {
            DataSource dataSource = getDS();
            logger.trace(methodName + ":dataSource:" + dataSource);
            // Create a QueryRunner that will use connections from
            // the given DataSource
            
            QueryRunner run = new QueryRunner(dataSource);
            ResultSetHandler rsh = new MapListHandler();
            data = (List) run.query(sql, params, rsh);
            //data = (List) run.query(sql, rsh);
           
           

        } catch (SQLException e) {
            e.printStackTrace();
            
            //System.out.println("SQL Error -" + e.getMessage()) ;
            throw e ;
            //throw new SapMDWsException(e.getMessage()) ;
        }
        //logger.debug(methodName + ":returning:" + data);
        return data;
    }

    public Map getData(String sql, Object[] params) throws SapMDWsException {
        Map data = null;
        // debugs/tracking
        String methodName = "getListData(" + "" + ")";
        String methodNameLong = "getListData(" + sql + ")";
        logger.debug(methodName);

        ObjectMapper mapper = new ObjectMapper();
        try {
            DataSource dataSource = getDS();
            logger.trace(methodName + ":dataSource:" + dataSource);
            // Create a QueryRunner that will use connections from
            // the given DataSource

            QueryRunner run = new QueryRunner(dataSource);
            ResultSetHandler rsh = new MapHandler();
            data = (Map) run.query(sql, params, rsh);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        //logger.debug(methodName + ":returning:" + data);
        return data;
    }

    ///////////////////////////////////////
    //.. result handlers
    ResultSetHandler<Map<String, String>> oneResultHandler = new ResultSetHandler<Map<String, String>>() {
        public Map<String, String> handle(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return null;
            }
            Map<String, String> map = new HashMap();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            // Object[] result = new Object[cols];

            for (int i = 1; i < cols+1; i++) {
                System.out.println("Column name: " + meta.getColumnName(i));
                System.out.println("Column label: " + meta.getColumnLabel(i));
                System.out.println("Object: " + rs.getString(i));
                map.put(meta.getColumnName(i), rs.getString(i));

            }
            System.out.println("Returning map: " + map);
            return map;
        }
    };

    
    ResultSetHandler<List> listResultHandler = new ResultSetHandler<List>() {
        public List<Map<String, String>> handle(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return null;
            }
            List list = new ArrayList();
            Map<String, String> map = null;
            try 
            {
                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();
                ObjectMapper mapper = new ObjectMapper();
                while (rs.next()) 
                {
                    System.out.println("--->>>> rs:   " + rs);
                  //  List l = 
                   //   System.out.println("rs String:   " + mapper.writeValueAsString(rs));
                    // Object[] result = new Object[cols];
                    for (int i = 1; i < cols+1; i++) {
                        map = new HashMap();
                      //  System.out.println("Column name: " + meta.getColumnName(i));
                      //  System.out.println("Column label: " + meta.getColumnLabel(i));
                      //  System.out.println("Object: " + rs.getString(i));
                        map.put(meta.getColumnName(i), rs.getString(i));
                        System.out.println("adding map: " + map);
                        list.add(map);
                    }

                }
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }
            System.out.println("Returning list: " + list);
            return list;
        }
         


    };
    //.. result handlers
    ResultSetHandler<Object[]> genericResultHandler = new ResultSetHandler<Object[]>() {
        public Object[] handle(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return null;
            }

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            Object[] result = new Object[cols];

            for (int i = 0; i < cols; i++) {
                result[i] = rs.getObject(i + 1);
            }

            return result;
        }
    };

    ResultSetHandler<Integer> intHandler = new ResultSetHandler<Integer>() {
        public Integer handle(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return null;
            }
            Integer data = -1;
            data = rs.getInt("COUNT");
            return data;
        }
    };

}
