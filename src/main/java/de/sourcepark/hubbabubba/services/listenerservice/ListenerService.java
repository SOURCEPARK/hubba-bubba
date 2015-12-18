package de.sourcepark.hubbabubba.services.listenerservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.HubbaBubba;
import de.sourcepark.hubbabubba.domain.CandyError;
import de.sourcepark.hubbabubba.domain.ListenerRegistration;
import de.sourcepark.hubbabubba.listener.CandyListener;
import de.sourcepark.hubbabubba.listener.CandyListenerRegistry;
import de.sourcepark.hubbabubba.services.CandyRoute;
import de.sourcepark.hubbabubba.services.CandyRouteDisabledException;
import de.sourcepark.hubbabubba.services.CandyService;
import de.sourcepark.hubbabubba.services.HTTPMethod;
import de.sourcepark.hubbabubba.services.RouteMap;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

/**
 * The ListenerService class is used to register/unregister listeners.
 * @author smatyba
 */
public class ListenerService extends CandyService {
    
     private static final transient Logger LOG = LoggerFactory.getLogger(ListenerService.class);
     
     public class RegisterRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            ObjectMapper mapper = new ObjectMapper();
            try {
                ListenerRegistration registration = mapper.readValue(request.body(), ListenerRegistration.class);
                if (registration.getListenerURL()==null || registration.getListenerURL().trim().length()<1) {
                    LOG.error("Error during a Listener Registration request: ListenerURL must not be null or empty");
                    final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_PARAM, HubbaBubba.ERROR_NAME_PARAM, "ListenerURL darf nicht null oder leer sein.");
                    response.status(503);
                    response.type("application/json");
                    return mapper.writeValueAsString(error);
                }
                if (registration.getListenerName()==null || registration.getListenerName().trim().length()<1) {
                    LOG.error("Error during a Listener Registration request: ListenerName must not be null or empty");
                    final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_PARAM, HubbaBubba.ERROR_NAME_PARAM, "ListenerName darf nicht null oder leer sein.");
                    response.status(503);
                    response.type("application/json");
                    return mapper.writeValueAsString(error);
                }
                CandyListener cl = new CandyListener(registration.getListenerURL(), registration.getListenerName());
                boolean alreadyConnected = CandyListenerRegistry.getInstance().addCandyListener(cl);
                return alreadyConnected;
            } catch (IOException ex) {
                LOG.error("IO problem occured during a Listener Registration request: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_GENERAL_IO, HubbaBubba.ERROR_NAME_GENERAL_IO, "IO-Probleme bei der Registrierung");
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
     
    public class UnregisterRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            ObjectMapper mapper = new ObjectMapper();
            try {
                ListenerRegistration registration = mapper.readValue(request.body(), ListenerRegistration.class);
                if (registration.getListenerName()==null || registration.getListenerName().trim().length()<1) {
                    LOG.error("Error during a Listener Unregistration request: ListenerName must not be null or empty");
                    final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_PARAM, HubbaBubba.ERROR_NAME_PARAM, "ListenerName darf nicht null oder leer sein.");
                    response.status(503);
                    response.type("application/json");
                    return mapper.writeValueAsString(error);
                }
                CandyListener cl = new CandyListener(registration.getListenerURL(), registration.getListenerName());
                boolean wasRegistered = CandyListenerRegistry.getInstance().removeCandyListener(cl);
                return wasRegistered;
            } catch (IOException ex) {
                LOG.error("IO problem occured during a Listener Unregistration request: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_GENERAL_IO, HubbaBubba.ERROR_NAME_GENERAL_IO, "IO-Probleme bei der Registrierung");
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }

    @Override
    public RouteMap initializeRoutes() {
        final RouteMap map = new RouteMap();
        final Map<String, CandyRoute> postMap = new HashMap<>();
        postMap.put("/listener/register", new ListenerService.RegisterRoute());
        postMap.put("/listener/unregister", new ListenerService.UnregisterRoute());
    
        map.put(HTTPMethod.POST, postMap);
        return map;
    }
    
    
    
}
