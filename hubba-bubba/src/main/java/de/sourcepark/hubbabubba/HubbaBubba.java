package de.sourcepark.hubbabubba;

import de.sourcepark.hubbabubba.services.ExampleCandyService;
import de.sourcepark.hubbabubba.services.HTTPMethod;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;
import static spark.Spark.*;

/**
 *
 * @author smatyba
 */
public class HubbaBubba {
    private static final transient Logger LOG = LoggerFactory.getLogger(HubbaBubba.class);
    
    public static final transient int PORT = 9999;
    
    public static void main(final String[] args) {
        LOG.debug("Starting server on port {}", PORT);
        port(PORT);
        
        final ExampleCandyService exServ = new ExampleCandyService();
        
        for(final Entry<HTTPMethod, Map<String, Route>> entry : exServ.getRoutes().entrySet()) {
            switch(entry.getKey()) {
                case GET: { 
                    for(final Entry<String, Route> mapping : entry.getValue().entrySet()) {
                        get(mapping.getKey(), mapping.getValue());
                    }
                    break;
                }
            }
        }
    }
}
