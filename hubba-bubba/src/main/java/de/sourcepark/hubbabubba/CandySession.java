/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba;

/**
 *
 * @author lsotoudeh
 */
public class CandySession {
    // the Duck
    private static CandySession instance;
    
    private static String controllerName;
    
    private static SessionState state;

    

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
            state = SessionState.NEW;
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
    public static CandySession getExistingInstance() {
        return CandySession.instance;
    }
    
    /**
     * returns the current state of the Session 
     * @return the current state of the Session specified by Enum SessionState
     */
    public static SessionState getState() {
        return state;
    }
    
    public void stateTransition(SessionState newState) {
        //FIXME
//        if (newState.getId()<state.getId()) {
//            throw new SessionStateTransitionException(state, newState);
//        }
        throw new UnsupportedOperationException();
    }
    
    public static void release() {
        controllerName = null;
        instance = null;
        state = null;
    }
}
