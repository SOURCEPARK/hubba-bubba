package de.sourcepark.hubbabubba.util;

import de.sourcepark.hubbabubba.services.CandyService;

/**
 * The ServiceLoader class is the abstract base class for all Service loading 
 * classes utilized by the central hub.
 * @author smatyba
 */
public abstract class ServiceLoader {
    public abstract CandyService[] loadAll();
}
