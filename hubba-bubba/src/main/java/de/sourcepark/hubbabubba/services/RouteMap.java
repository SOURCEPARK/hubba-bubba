package de.sourcepark.hubbabubba.services;

import java.util.HashMap;
import java.util.Map;
import spark.Route;

/**
 * The RouteMap is a convenience class that wraps a HashMap of HTTPMethod values 
 * to maps of Strings to Routes for increased readability.
 * @author smatyba
 */
public class RouteMap extends HashMap<HTTPMethod, Map<String, Route>> {

}