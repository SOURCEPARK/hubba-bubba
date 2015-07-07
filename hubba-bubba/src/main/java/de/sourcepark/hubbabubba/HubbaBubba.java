package de.sourcepark.hubbabubba;

import de.sourcepark.hubbabubba.services.CandyService;
import de.sourcepark.hubbabubba.services.ExampleCandyService;
import de.sourcepark.hubbabubba.services.HTTPMethod;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;
import static spark.Spark.get;
import static spark.SparkBase.port;

/**
 * The actual main (server) class.
 * @author smatyba
 */
public class HubbaBubba {
    private static final transient Logger LOG = LoggerFactory.getLogger(HubbaBubba.class);
    
    public final void startServer(final int port, final CandyService... services) {
        LOG.debug("Starting server on port {}", port);
        port(port);
        
        for(final CandyService service : services) {
            LOG.info("Registering candy service '{}'...", service.getName());
        }
        
        final ExampleCandyService exServ = new ExampleCandyService();
        
        for(final Map.Entry<HTTPMethod, Map<String, Route>> entry : exServ.getRoutes().entrySet()) {
            switch(entry.getKey()) {
                case GET: { 
                    for(final Map.Entry<String, Route> mapping : entry.getValue().entrySet()) {
                        get(mapping.getKey(), mapping.getValue());
                    }
                    break;
                }
            }
        }
    }
    
}
