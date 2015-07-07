package de.sourcepark.hubbabubba.services;

import java.util.Map;
import spark.Route;

/**
 *
 * @author smatyba
 */
public abstract class CandyService {
    private String name = "Just another candy service";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract RouteMap getRoutes();    
}
