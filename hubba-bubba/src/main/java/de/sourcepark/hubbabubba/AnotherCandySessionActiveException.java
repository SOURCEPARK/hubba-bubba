/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba;

/**
 * Exception is thrown if a controller request the Session control, but another
 * controller is active
 * @author lsotoudeh
 */
class AnotherCandySessionActiveException extends Exception {
    
    public AnotherCandySessionActiveException(String message) {
        super(message);
    }
    
}
