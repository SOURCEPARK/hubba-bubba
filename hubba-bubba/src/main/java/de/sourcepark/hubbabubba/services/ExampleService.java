package de.sourcepark.hubbabubba.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.annotation.CandyService;
import de.sourcepark.hubbabubba.domain.Example;
import spark.Request;
import spark.Response;

/**
 *
 * @author smatyba
 */
@CandyService(route = "/example")
public class ExampleService extends AbstractCandyService {

    @Override
    public Object handleGetRequest(Request request, Response response) throws JsonProcessingException {
        final Example example = new Example();
        example.setExampleString("This is from the ExampleService class");
        final ObjectMapper mapper = new ObjectMapper();
        
        
        response.status(200);
        response.type("application/json");
        
        return mapper.writeValueAsString(example);
    }
    
}
