package com.pantheonsorbonne.infocovid.config;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Configuration for SSL certificate
 */
public class SSLValidation {
 
    private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers(){
                    return null;
                }
                public void checkClientTrusted( X509Certificate[] certs, String authType ){}
                public void checkServerTrusted( X509Certificate[] certs, String authType ){}
            }
    };
    
 /**
  * Install the all-trusting trust manager
  * @throws NoSuchAlgorithmException
  * @throws KeyManagementException
  */
    public static void turnOffSslChecking() throws NoSuchAlgorithmException, KeyManagementException { 
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init( null, UNQUESTIONING_TRUST_MANAGER, null );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
    
    /**
     * Return it to the initial state (discovered by reflection, now hardcoded)
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
 
    public static void turnOnSslChecking() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext.getInstance("SSL").init( null, null, null );
    }
 
    private SSLValidation(){
        throw new UnsupportedOperationException( "Do not instantiate libraries.");
    }
}
 