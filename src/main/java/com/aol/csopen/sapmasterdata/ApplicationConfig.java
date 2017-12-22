/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aol.csopen.sapmasterdata;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author pmysore1
 */
@javax.ws.rs.ApplicationPath("edx")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.aol.csopen.sapmasterdata.CompanyResource.class);
        resources.add(com.aol.csopen.sapmasterdata.ExchangeRateResource.class);
        resources.add(com.aol.csopen.sapmasterdata.GlaccountResource.class);
        resources.add(com.aol.csopen.sapmasterdata.P2PComapny.class);
        resources.add(com.aol.csopen.sapmasterdata.ProfitcenterResource.class);
        resources.add(com.aol.csopen.sapmasterdata.SAPCustomerResource.class);
        resources.add(com.aol.csopen.sapmasterdata.SAPVendorResource.class);
        resources.add(com.aol.csopen.sapmasterdata.WbsResource.class);
    }
    
}
