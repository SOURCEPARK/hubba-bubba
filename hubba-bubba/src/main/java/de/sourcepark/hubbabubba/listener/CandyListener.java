/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.listener;

import java.util.Objects;

/**
 *
 * @author lsotoudeh
 */
public class CandyListener {
    
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
