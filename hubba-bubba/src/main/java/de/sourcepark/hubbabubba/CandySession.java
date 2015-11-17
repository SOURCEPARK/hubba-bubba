/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba;

import de.sourcepark.hubbabubba.services.authorization.User;
import de.sourcepark.hubbabubba.state.ICandySessionState;
import de.sourcepark.hubbabubba.state.SessionStartState;

/**
 *
 * @author lsotoudeh
 */
public class CandySession implements ICandySessionState {
    // the Duck
    private static CandySession instance;
    
    private static String controllerName;
    
    private static ICandySessionState state;

    private static User user;


    // Singleton constructor
    private CandySession() {
    }

    /**
     * Constructor providing a new Singleton CandySession assuming, there is no open CandySession yet
     * @param controllerName the name of the controller requesting the new instance
     * @return the Singleton CandySession
     * @throws de.sourcepark.hubbabubba.AnotherCandySessionActiveException
     */
    public static CandySession getNewInstance(String controllerName) throws AnotherCandySessionActiveException {
        if (CandySession.instance == null) {
            CandySession.instance = new CandySession();
            //FIXME: Konfigurationsabfrage der zulässigen Controller
            CandySession.controllerName = controllerName;
            state = new SessionStartState();
        } else {
            if (!controllerName.equalsIgnoreCase(CandySession.controllerName)) {
                throw new AnotherCandySessionActiveException(CandySession.controllerName + " is active.");
            }
        }
        return CandySession.instance;
    }
    
    /**
     * Constructor providing a Singleton CandySession, if there is an open one
     * @return the Singleton CandySession, if there is an open one
     */
    public CandySession getExistingInstance() {
        return CandySession.instance;
    }
    
    /**
     * returns the current state of the Session 
     * @return the current state of the Session
     */
    public ICandySessionState getState() {
        return state;
    }
    
    /**
     * sets the current state of the Session 
     * @param state the new state of the Session
     */
    public void setState(ICandySessionState state) {
        CandySession.state = state;
    }
    
    
    
    public static void release() {
        controllerName = null;
        instance = null;
        state = null;
        user = null;
    }

    @Override
    public void doAction() {
        CandySession.state.doAction();
    }
    
    
    /**
     * @return the user
     */
    public static User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public static void setUser(User user) {
        CandySession.user = user;
    }
}
