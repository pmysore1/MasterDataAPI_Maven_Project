/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata.utils;

/**
 *
 * @author pmysore1
 */
public interface SAPMDFromEdxConstants {
    public static String INPUT_DATE_FMT = "MM/dd/yyyy";
    public static int INPUT_DATE_LEN = INPUT_DATE_FMT.length();

    public static String MY_CATALOG = "finance";
    public static String MY_SUB_CATALOG = "OTC";
    public static String MY_DEFAULT_SOURCE = "EDX-OTC";
    public static String SAP_SOURCE = "SAP";
    public static String MY_DEFAULT_FRESHNESS = "default";
    public static String MY_DEFAULT_DATA_TYPE = "default-data-type";

    public static String UNKNOWN_VAL = "N/A";
    public static String ELS_COOKIE_NAME = "iPlanetDirectoryPro";
    public static String REMOTE_USER = "REMOTE_USER";
    public static String PN_APP_ID = "appId";
    public static String PN_APP_KEY = "appKey";
    public static String PN_PWD = "pwd"; // not app password for els and ?
    public static String ELS_TOKEN_PREFIX = "token.id=";
    public static String PN_THE_DATA = "theData";
    public static String PN_PARAMS = "param";
    public static String PN_RESULTS = "result";

   // public static String PN_DATA = "data";
    public static String SUCCESS = "success";
    public static String FAILURE = "failure";
    public static String ERROR = "error";

}
