package de.sourcepark.hubbabubba;

import de.sourcepark.hubbabubba.services.ExampleCandyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main entry point for the application.
 * @author smatyba
 */
public class App {
    
    /**
     * Logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(App.class);
    
    /**
     * The default port on which servers are started.
     */
    public static final transient int PORT = 9999;
    
    /**
     * The application's main entry point. Starts a new server.
     * @param args Command line arguments, ignored so far
     */
    public static void main(final String[] args) {
        LOG.info("Starting the Central Candy Hub [Hubba-Bubba]...");
        final HubbaBubba server = new HubbaBubba();
        server.startServer(PORT, new ExampleCandyService());
    }
}