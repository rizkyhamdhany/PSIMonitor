package com.psi.monitor.controllers;

import static com.psi.monitor.BuildConfig.API_CONFIG;

/**
 * Created by hamdhanywijaya@gmail.com on 8/16/17.
 */

public class EndPoint {
    public static String PRODUCTION_DOMAIN = "https://api.data.gov.sg/";
    public static String STAGING_DOMAIN = "https://api.data.gov.sg/";
    public static String VERSION = "v1/";
    public static String PATH = "environment/";

    public final static String psi = "psi";

    /**
     * method to get this domain
     * @return
     */
    public static String getDomain(){
        return API_CONFIG == ApiConfig.PROD ? PRODUCTION_DOMAIN : STAGING_DOMAIN;
    }

    /**
     * method to get base uri
     *
     * @return
     */
    public static String getApiBaseUri(){
        return getDomain() + VERSION + PATH;
    }
}
