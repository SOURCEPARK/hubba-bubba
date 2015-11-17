/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.listener;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author lsotoudeh
 */
public class CandyListenerRegistry {
    // the Duck
    private static CandyListenerRegistry instance;
    
    private static Set<CandyListener> listeners;


    // Singleton constructor
    private CandyListenerRegistry() {
        listeners = new HashSet<>();
    }

    /**
     * Constructor providing a Singleton CandyListenerRegistry
     * @return the Singleton CandyListenerRegistry
     */
    public static CandyListenerRegistry getInstance() {
        if (CandyListenerRegistry.instance == null) {
            CandyListenerRegistry.instance = new CandyListenerRegistry();
        } 
        return CandyListenerRegistry.instance;
    }
    
    /**
     * adds a CandyListener to the registry
     * @param cl the CandyListener to add
     * @return true if the list changed as a result of the call, false if a CandyListener with the hostname was already part of the list
     */
    public boolean addCandyListener(CandyListener cl) {
        return listeners.add(cl);
    }
    
    /**
     * removes a CandyListener from the Registry
     * @param cl the CandyListener to remove
     * @return true if the specified CandyListener (i.e. a Listener with the given hostname) was part of the list
     */
    public boolean removeCandyListener(CandyListener cl) {
        return listeners.remove(cl);
    }
    
    /**
     * removes a CandyListener from the Registry
     * @param host the hostname of the CandyListener to remove
     * @return true if a CandyListener with the given hostname was part of the list
     */
    public boolean removeCandyListener(String host) {
        CandyListener tmpListener = new CandyListener(host, "tmpCompListener");
        return listeners.remove(tmpListener);
    }
    
    /**
     * checks if a CandyListener is already registered (disciminated by host)
     * @param cl the CandyListener to check
     * @return true, if the Registry already contains the given CandyListener (isciminated by host)
     */
    public boolean contains(CandyListener cl) {
        return listeners.contains(cl);
    }
    
    /**
     * returns the registered CandyListeners
     * @return the registered CandyListeners
     */
    public Set<CandyListener> getListeners() {
         return listeners;
     }
}
