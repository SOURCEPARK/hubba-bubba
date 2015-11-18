package de.sourcepark.hubbabubba.services.audio;

import de.sourcepark.hubbabubba.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sourcepark.hubbabubba.state.CandySessionState;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;

/**
 * The AudioPlayerCandyService class is exactly that: an example candy service
 * mapping the routes /example and /test to an instance of
 * {@code AudioPlayerGetRoute}.
 *
 * @author mbarmeier
 */
public class AudioPlayer extends CandyService {

    /**
     * Initializes a new instance of the AudioPlayerCandyService class.
     */
    public AudioPlayer() {
	this.setName("Audio Service");
	this.enable();
    }
    
    /**
     * The AudioPlayerGetRoute class is an example implementation of a Spark Route.
     * It will create a new {@code AudioPlayer} instance and convert it to a JSON 
     * String before returning it (which is handling the request made by the 
     * client, btw).
     */
    public class AudioPlayerRoute extends CandyRoute {

	/**
	 * Handles the request (a GET request in this case).
	 *
	 * @param request The actual request object
	 * @param response The actual response object
	 * @return An instance of {@code AudioPlayer} converted to JSON
	 * @throws CandyRouteDisabledException If Route is disabled.
	 * @throws com.fasterxml.jackson.core.JsonProcessingException in case of
	 * conversion problems
	 */
	@Override
	public Object handle(Request request, Response response) throws CandyRouteDisabledException, JsonProcessingException {
	    if (!isEnabled()) {
		throw new CandyRouteDisabledException();
	    }
	    final AudioPlayer audioPlayer = new AudioPlayer();
	    String state = request.params("state");
	    switch (state) {
		case CandySessionState.MAINTAINER_STATE: {
		    try {
			Process p = Runtime.getRuntime().exec("/usr/bin/mplayer /home/candy/maintenance.mp3");
		    } catch (IOException ex) {
			Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    break;
		}
		case CandySessionState.SELECTER_STATE: {
		    try {
			Process p = Runtime.getRuntime().exec("/usr/bin/mplayer /home/candy/candyshop.mp3");
		    } catch (IOException ex) {
			Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    break;
		}
		case CandySessionState.DISPENSER_STATE: {
		    try {
			Process p = Runtime.getRuntime().exec("/usr/bin/mplayer /home/candy/sugarshort.mp3");
		    } catch (IOException ex) {
			Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    break;
		}
		case CandySessionState.UNEXPECTED_ERROR_STATE: {
		    try {
			Process p = Runtime.getRuntime().exec("/usr/bin/mplayer /home/candy/don't_panic.mp3");
		    } catch (IOException ex) {
			Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    break;
		}
	    }
	    final ObjectMapper mapper = new ObjectMapper();

	    response.status(
		    200);
	    response.type(
		    "text/html");
	    return mapper.writeValueAsString(
		    "OK");
	}
    }

    /**
     * Generates and returns a {@code RouteMap} representing all the routes and
     * handlers this service provides.
     *
     * @return A {@code RouteMap} containing all routes/handlers this service
     * provides.
     */
    @Override
    public RouteMap initializeRoutes() {
	final RouteMap map = new RouteMap();
	final Map<String, CandyRoute> getMap = new HashMap<>();
	getMap.put("/audio/:state", new AudioPlayerRoute());
	map.put(HTTPMethod.GET, getMap);
	return map;
    }
}
