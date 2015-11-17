/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.state;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import de.sourcepark.hubbabubba.listener.CandyListener;
import de.sourcepark.hubbabubba.listener.CandyListenerRegistry;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lsotoudeh
 */
public abstract class CandySessionState {
    
    private static final transient Logger LOG = LoggerFactory.getLogger(CandySessionState.class);
    
    private String identifier;
    
    public static final String DISPENSER_STATE = "DISPENSING";
    public static final String MAINTAINER_STATE = "MAINTENANCE";
    public static final String SELECTER_STATE = "SELECTING";
    public static final String SESSION_START_STATE = "SESSION_STARTING";
    public static final String SUCCESSFULLY_DISPENSED_STATE = "SUCCESSFULLY_DISPENSED";
    public static final String TERMINATOR_STATE = "TERMINATING_SESSION";
    public static final String UNEXPECTED_ERROR_STATE = "UNEXPECTED_ERROR";

    /**
     * Get the value of identifier
     *
     * @return the value of identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Set the value of identifier
     *
     * @param identifier new value of identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    
    public void doAction() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        LOG.debug("Sending state to CandyListeners: " + identifier);
        for (CandyListener listener : CandyListenerRegistry.getInstance().getListeners()) {
            String uri = listener.getHost();
            if (!uri.endsWith("/")) {
                uri+='/';
            }
            WebResource webResource = client.resource(UriBuilder.fromUri(uri + getIdentifier()).build());
            try {
                ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class);
                String responseText = response.getEntity(String.class);
                if (responseText.equals("OK")) {
                    LOG.debug("Successfully sent state update (" + identifier + ") to Listener: " + listener.getName());
                } else {
                    LOG.error("Error responded during state update (" + identifier + ") for Listener: " + listener.getName() + ", response was:\n\t" + responseText);
                }
            } catch (ClientHandlerException | UniformInterfaceException ex) {
                LOG.error("Unexpected error sending state update (" + identifier + ") to Listener: " + listener.getName(),ex);
            }
        }
    }
}
