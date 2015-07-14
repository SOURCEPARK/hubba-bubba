package de.sourcepark.hubbabubba.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.domain.Example;
import de.sourcepark.hubbabubba.domain.KeypadResponse;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

/**
 *
 * @author smatyba
 */
public class KeypadService extends CandyService {

    private static final transient Logger LOG = LoggerFactory.getLogger(KeypadService.class);
    
    public class KeypadInputRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws Exception {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            
            final KeypadResponse kpResponse = new KeypadResponse();
            kpResponse.setDescription("HubbaBubba handled your input request");
            kpResponse.setText("OK");
            kpResponse.setEcho(request.params(":value"));
            
            final ObjectMapper mapper = new ObjectMapper();
            response.status(200);
            response.type("application/json");
            return mapper.writeValueAsString(kpResponse);
        }
    }
    
    @Override
    public RouteMap initializeRoutes() {
        final RouteMap map = new RouteMap();
        final Map<String, CandyRoute> postMap = new HashMap<>();
        postMap.put("/keypad/enter/:value", new KeypadService.KeypadInputRoute());
        map.put(HTTPMethod.POST, postMap);
        return map;
    }
    
    
}
