package de.sourcepark.hubbabubba.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.domain.Example;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;

/**
 * The ExampleCandyService class is exactly that: an example candy service mapping 
 * the routes /example and /test to an instance of {@code ExampleGetRoute}.
 * @author smatyba
 */
public class ExampleCandyService extends CandyService {

    /**
     * Initializes a new instance of the ExampleCandyService class.
     */
    public ExampleCandyService() {
        this.setName("Example Candy Service");
        this.enable();
    }
    
    /**
     * The ExampleGetRoute class is an example implementation of a Spark Route.
     * It will create a new {@code Example} instance and convert it to a JSON 
     * String before returning it (which is handling the request made by the 
     * client, btw).
     */
    public class ExampleGetRoute extends CandyRoute {

        /**
         * Handles the request (a GET request in this case).
         * @param request The actual request object
         * @param response The actual response object
         * @return An instance of {@code Example} converted to JSON
         * @throws CandyRouteDisabledException If Route is disabled.
         * @throws com.fasterxml.jackson.core.JsonProcessingException in case of conversion problems
         */
        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if (!isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            final Example example = new Example();
            example.setExampleString("This is from the ExampleService class");
            final ObjectMapper mapper = new ObjectMapper();
            response.status(200);
            response.type("application/json");
            return mapper.writeValueAsString(example);
        }
    }

    /**
     * Generates and returns a {@code RouteMap} representing all the routes 
     * and handlers this service provides.
     * @return A {@code RouteMap} containing all routes/handlers this service
     * provides.
     */
    @Override
    public RouteMap initializeRoutes() {
        final RouteMap map = new RouteMap();
        final Map<String, CandyRoute> getMap = new HashMap<>();
        getMap.put("/example", new ExampleGetRoute());
        getMap.put("/test", new ExampleGetRoute());
        map.put(HTTPMethod.GET, getMap);
        return map;
    }
}