package de.sourcepark.hubbabubba.services;

import de.sourcepark.hubbabubba.services.duck.Duck;
import java.io.IOException;
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
public class ControlUnitService extends CandyService {

    private static final transient Logger LOG = LoggerFactory.getLogger(ControlUnitService.class);
    
    public class OrderRoute extends CandyRoute {

        @Override
        public Object handle(Request request, Response response) throws CandyRouteDisabledException {
            if(!this.isEnabled()) {
                throw new CandyRouteDisabledException();
            }
            try {
                Duck.getInstance("http://192.168.38.95/cgi-bin/duckcgi.py").orderCandy(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK order", ex);
                return "IOERR";
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
                Duck.getInstance("http://192.168.38.95/cgi-bin/duckcgi.py").calibrate(request.params("no"));
                return "OK";
            } catch (IOException ex) {
                LOG.error("IO problem occured during a DUCK calibration", ex);
                return "IOERR";
            }
            
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
        map.put(HTTPMethod.POST, postMap);
        final Map<String, CandyRoute> getMap = new HashMap<>();
        getMap.put("/control/order", new ControlUnitService.InfoRoute());
        map.put(HTTPMethod.GET, getMap);
        return map;
    }
    
    
}
