package de.sourcepark.hubbabubba.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.domain.Example;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author smatyba
 */
public class ExampleCandyService extends CandyService {

    public ExampleCandyService() {
        this.setName("Example Candy Service");
    }
    
    public class ExampleGetRoute implements Route {

        @Override
        public Object handle(Request request, Response response) throws Exception {
            final Example example = new Example();
            example.setExampleString("This is from the ExampleService class");
            final ObjectMapper mapper = new ObjectMapper();


            response.status(200);
            response.type("application/json");

            return mapper.writeValueAsString(example);
        }
    }

    @Override
    public RouteMap getRoutes() {
        final RouteMap map = new RouteMap();
        final Map<String, Route> getMap = new HashMap<>();
        getMap.put("/example", new ExampleGetRoute());
        getMap.put("/test", new ExampleGetRoute());
        map.put(HTTPMethod.GET, getMap);
        return map;
    }
    
}
