package de.sourcepark.hubbabubba.services;

import java.util.Map;
import spark.Route;

/**
 * The CandyService class is the abstract base class for all candy services.
 * 
 * A CandyService instance is something that provides handlers (Routes) for 
 * given URLs.
 * @author smatyba
 */
public abstract class CandyService {
    /**
     * This service's name.
     */
    private String name = "Just another candy service";

    /**
     * Gets this service's name.
     * @return A String containing the name of this service.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this service's name.
     * @param name The new name for this service.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * When implemented in a class, this method returns the routes this service 
     * provides. 
     * 
     * The {@code RouteMap} this method returns contains a mapping between URLs 
     * and the corresponding route instances mapped to the appropriate HTTP methods.
     * @return An instance of {@code RouteMap} representing all routes this service
     * can handle.
     */
    public abstract RouteMap getRoutes();    
}
