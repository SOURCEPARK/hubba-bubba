package de.sourcepark.hubbabubba.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.Config;
import de.sourcepark.hubbabubba.HubbaBubba;
import de.sourcepark.hubbabubba.domain.CandyError;
import de.sourcepark.hubbabubba.services.authorization.Authorization;
import de.sourcepark.hubbabubba.services.authorization.User;
import de.sourcepark.hubbabubba.services.duck.Duck;
import java.io.FileNotFoundException;
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
       
    public class DUCKOrderRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            
            try {
                Duck.getInstance(Config.duckURL).orderCandy(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK order command: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_DUCK_IO, HubbaBubba.ERROR_NAME_DUCK_IO, "DUCK ist nicht erreichbar");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
    
    public class DUCKCalibrateRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance(Config.duckURL).calibrate(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK calibration command: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_DUCK_IO, HubbaBubba.ERROR_NAME_DUCK_IO, "DUCK ist nicht erreichbar");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
    
    public class DUCKStepRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance(Config.duckURL).step(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK step command: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_DUCK_IO, HubbaBubba.ERROR_NAME_DUCK_IO, "DUCK ist nicht erreichbar");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
    
    public class DUCKMotorRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance(Config.duckURL).motor(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK motor command: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_DUCK_IO, HubbaBubba.ERROR_NAME_DUCK_IO, "DUCK ist nicht erreichbar");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
    
    public class DUCKColOnRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance(Config.duckURL).colOn(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                 LOG.error("IO problem occured during a DUCK colOn command: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_DUCK_IO, HubbaBubba.ERROR_NAME_DUCK_IO, "DUCK ist nicht erreichbar");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
    
    public class DUCKRowOnRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance(Config.duckURL).rowOn(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                 LOG.error("IO problem occured during a DUCK rowOn command: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_DUCK_IO, HubbaBubba.ERROR_NAME_DUCK_IO, "DUCK ist nicht erreichbar");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
    
    public class DUCKAllOffRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance(Config.duckURL).allOff();
                return "OK";
            } catch (IOException ex) {
                 LOG.error("IO problem occured during a DUCK allOff command: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_DUCK_IO, HubbaBubba.ERROR_NAME_DUCK_IO, "DUCK ist nicht erreichbar");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
        }
    }
    
    public class AuthorizeRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            
            try {
                User user = Authorization.getInstance(Config.authorizationFilePath).authorize(request.params("id"));
                if (user!=null) { 
                    final ObjectMapper mapper = new ObjectMapper();
                    response.type("application/json");
                    return mapper.writeValueAsString(user);
                } else {
                    final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_USER_NOT_FOUND,HubbaBubba.ERROR_NAME_USER_NOT_FOUND, "Die Person ist nicht autorisiert.");                
                    final ObjectMapper mapper = new ObjectMapper();
                    response.status(503);
                    response.type("application/json");
                    return mapper.writeValueAsString(error);
                }
            } catch (FileNotFoundException ex) {
                LOG.error("User Registry File not found: {}", Config.authorizationFilePath, ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_USER_REGISTRY_NOT_FOUND,HubbaBubba.ERROR_NAME_USER_REGISTRY_NOT_FOUND, "Die Benutzer-Registry konnte nicht zur Autorisierung gefunden werden.");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            } catch (IOException ex) {
                LOG.error("User Registry IO error: {}", ex.getMessage(), ex);
                final CandyError error = new CandyError(HubbaBubba.ERROR_CODE_USER_REGISTRY_IO,HubbaBubba.ERROR_NAME_USER_REGISTRY_IO, "Die Benutzer-Registry konnte nicht gelesen werden (IO).");                
                final ObjectMapper mapper = new ObjectMapper();
                response.status(503);
                response.type("application/json");
                return mapper.writeValueAsString(error);
            }
            
//            try {
//                CandySession activeSession = CandySession.getInstance(request.ip());
//            } catch (AnotherCandySessionActiveException ex) {
//                java.util.logging.Logger.getLogger(ControlUnitService.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
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
        postMap.put("/control/order/:no", new ControlUnitService.DUCKOrderRoute());
        postMap.put("/control/calibrate/:no", new ControlUnitService.DUCKCalibrateRoute());
        postMap.put("/control/step/:no", new ControlUnitService.DUCKStepRoute());
        postMap.put("/control/motor/:no", new ControlUnitService.DUCKMotorRoute());
        postMap.put("/control/colon/:no", new ControlUnitService.DUCKColOnRoute());
        postMap.put("/control/rowon/:no", new ControlUnitService.DUCKRowOnRoute());
        postMap.put("/control/alloff", new ControlUnitService.DUCKAllOffRoute());
        postMap.put("/control/authorize/:id", new ControlUnitService.AuthorizeRoute());
    
        map.put(HTTPMethod.POST, postMap);
        final Map<String, CandyRoute> getMap = new HashMap<>();
        getMap.put("/control/order", new ControlUnitService.InfoRoute());
        map.put(HTTPMethod.GET, getMap);
        return map;
    }
    
    
}
