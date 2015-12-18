/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.listener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lsotoudeh
 */
public class CandyListener {
    
        
    /**
     * Logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(CandyListener.class);
    
    private String host;
        
    private String name;

    public CandyListener(String host, String name) {
        this.host = host;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CandyListener)) {
            return false;
        } else {
            return ((CandyListener)obj).getHost().equalsIgnoreCase(host);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.host);
        return hash;
    }
//    
//    public void processRESTCommand(String command) throws MalformedURLException, IOException {
//        String url = host+"/"+command;
//        URL urlObj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
//        con.setRequestMethod("POST");
//        LOG.debug("Sending 'POST' request for command " + command + " to CandyListener : " + name);
//        int responseCode = con.getResponseCode();
//        LOG.debug("CandyListener " + name + " responded: " + responseCode);
//    }
    
    

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Get the value of host
     *
     * @return the value of host
     */
    public String getHost() {
        return host;
    }

    /**
     * Set the value of host
     *
     * @param host new value of host
     */
    public void setHost(String host) {
        this.host = host;
    }

    
}
