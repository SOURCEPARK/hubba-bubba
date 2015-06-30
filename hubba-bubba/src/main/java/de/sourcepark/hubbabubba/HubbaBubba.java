package de.sourcepark.hubbabubba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.annotation.CandyService;
import de.sourcepark.hubbabubba.domain.Example;
import de.sourcepark.hubbabubba.services.AbstractCandyService;
import de.sourcepark.hubbabubba.services.ExampleService;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 *
 * @author smatyba
 */
public class HubbaBubba {
    private static final transient Logger LOG = LoggerFactory.getLogger(HubbaBubba.class);
    
    public static final transient int PORT = 9999;
    
    private static Object handleGet(Request request, Response response) throws JsonProcessingException {
        final Example ex = new Example();
            
        final ObjectMapper mapper = new ObjectMapper();

        response.status(200);
        response.type("application/json");
        return mapper.writeValueAsString(ex);
    }
    
    private static Object handleControlServiceGet(Request request, Response response) throws JsonProcessingException {
        LOG.debug("Control Service /get: {}", request.toString());
        final Example ex = new Example();
        final ObjectMapper mapper = new ObjectMapper();
        response.status(200);
        response.type("application/json");
        return mapper.writeValueAsString(ex);
    }
    
    private final List<AbstractCandyService> services = new ArrayList<>();
    
    private static final Map<String, AbstractCandyService> serviceMap = new HashMap<>();
    
    private static void mapServices() {
        final ExampleService service = new ExampleService();
        final Class serviceClass = ExampleService.class;
        final Annotation[] annotations = serviceClass.getDeclaredAnnotationsByType(CandyService.class);
        
        if(annotations != null) {
            for(final Annotation annotation : annotations) {
                serviceMap.put(((CandyService)annotation).route(), service);
            }
        }
    }
    
    public static void main(final String[] args) {
        LOG.debug("Starting server on port {}", PORT);
        mapServices();
        port(PORT);
        
        for(final Entry<String, AbstractCandyService> entry : serviceMap.entrySet()) {
            get(entry.getKey(), (req, res) -> entry.getValue().handleGetRequest(req, res));
        }
        
//        get("/hello", (req, res) -> handleGet(req, res));
//        get("/control", (req, res) -> handleControlServiceGet(req, res));

    }
}
