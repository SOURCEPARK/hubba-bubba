package de.sourcepark.hubbabubba.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.AnotherCandySessionActiveException;
import de.sourcepark.hubbabubba.CandySession;
import de.sourcepark.hubbabubba.Config;
import de.sourcepark.hubbabubba.HubbaBubba;
import de.sourcepark.hubbabubba.domain.CandyError;
import de.sourcepark.hubbabubba.services.duck.Duck;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

/**
 *
 * @author smatyba
 */
public class ControlUnitService extends CandyService {

    private static final transient Logger LOG = LoggerFactory.getLogger(ControlUnitService.class);
       
    public class OrderRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            
            try {
                Duck.getInstance(Config.duckURL).orderCandy(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK order: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_DUCK_IO, HubbaBubba.ERROR_NAME_DUCK_IO, "DUCK ist nicht erreichbar");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
    
    public class CalibrateRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance(Config.duckURL).calibrate(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK calibration", ex);
                return "IOERR";
            }
            
        }
    }
    
    public class StepRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance(Config.duckURL).step(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK step", ex);
                return "IOERR";
            }
            
        }
    }
    
    public class AuthorizeRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
//            try {
//                CandySession activeSession = CandySession.getInstance(request.ip());
//            } catch (AnotherCandySessionActiveException ex) {
//                java.util.logging.Logger.getLogger(ControlUnitService.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            throw new UnsupportedOperationException("not yet implemented");
            //return CandyUser-object
            
        }
    }
    
    
    public class InfoRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            return "Control Unit Service Info: Up and running...";
            
        }
    }
    
    @Override
    public RouteMap initializeRoutes() {
        final RouteMap map = new RouteMap();
        final Map<String, CandyRoute> postMap = new HashMap<>();
        postMap.put("/control/order/:no", new ControlUnitService.OrderRoute());
        postMap.put("/control/calibrate/:no", new ControlUnitService.CalibrateRoute());
        postMap.put("/control/step/:no", new ControlUnitService.StepRoute());
        postMap.put("/control/authorize/:user", new ControlUnitService.AuthorizeRoute());
    
        map.put(HTTPMethod.POST, postMap);
        final Map<String, CandyRoute> getMap = new HashMap<>();
        getMap.put("/control/order", new ControlUnitService.InfoRoute());
        map.put(HTTPMethod.GET, getMap);
        return map;
    }
    
    
}
