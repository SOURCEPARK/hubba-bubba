/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.services.duck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lsotoudeh
 */
public class Duck {
    
    /**
     * Logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(Duck.class);
    
    private static String duckURL = "";
    private final static String orderCommand = "?command=e";
    private final static String calibrateCommand = "?command=c";
    private final static String stepCommand = "?command=s";

    // the Duck
    private static Duck instance;

    // Singleton constructor
    private Duck() {
    }

    /**
     * Constructor providing a Singleton Duck
     * @param duckURL the URL for contacting the poultry
     * @return the Singleton Duck
     */
    public static Duck getInstance(String duckURL) {
        if (Duck.instance == null) {
            Duck.instance = new Duck();
            Duck.duckURL = duckURL;
        }
        return Duck.instance;
    }

    public static void orderCandy(String address) throws MalformedURLException, IOException {
        //FIXME: address validation???
        String url = duckURL+orderCommand+address;
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        LOG.debug("Sending 'GET' request for order to URL : " + url);
        LOG.debug("Response Code : " + responseCode);
        StringBuffer response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        //print result
        LOG.debug("Duck responded: "+response.toString());
    }
    
    public static void calibrate(String address) throws MalformedURLException, IOException {
        //FIXME: address validation???
        String url = duckURL+calibrateCommand+address;
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        LOG.debug("Sending 'GET' request for calibration to URL : " + url);
        LOG.debug("Response Code : " + responseCode);
        StringBuffer response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        //print result
        LOG.debug("Duck responded: "+response.toString());
    }
    
    public static void step(String address) throws MalformedURLException, IOException {
        //FIXME: address validation???
        String url = duckURL+stepCommand+address;
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        LOG.debug("Sending 'GET' request for step to URL : " + url);
        LOG.debug("Response Code : " + responseCode);
        StringBuffer response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        //print result
        LOG.debug("Duck responded: "+response.toString());
    }
}
