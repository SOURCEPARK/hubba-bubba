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

    // Singleton constructor
    private CandySession() {
    }

    /**
     * Constructor providing a Singleton CandySession
     * @param controllerName the name of the controller requesting the instance
     * @return the Singleton CandySession
     */
    public static CandySession getInstance(String controllerName) throws AnotherCandySessionActiveException {
        if (CandySession.instance == null) {
            CandySession.instance = new CandySession();
            CandySession.controllerName = controllerName;
        } else {
            if (!controllerName.equalsIgnoreCase(CandySession.controllerName)) {
                throw new AnotherCandySessionActiveException(CandySession.controllerName + " is active.");
            }
        }
        return CandySession.instance;
    }
    
    public static void release() {
        controllerName = null;
        instance = null;
    }
}
