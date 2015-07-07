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
 * The main entry point for the application.
 * @author smatyba
 */
public class App {
    private static final transient Logger LOG = LoggerFactory.getLogger(App.class);
    
    public static final transient int PORT = 9999;
    
    public static void main(final String[] args) {
        
        final HubbaBubba server = new HubbaBubba();
        server.startServer(PORT, new ExampleCandyService());
    }
}
