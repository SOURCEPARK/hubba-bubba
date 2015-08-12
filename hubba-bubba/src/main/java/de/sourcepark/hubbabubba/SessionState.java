/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba;

/**
 * FIXME: noch nicht benutzen, ist noch Quatsch!!!
 * @author lsotoudeh
 */
public enum SessionState {
    NEW (0),
    INITIALIZED (10),
    WAITING_FOR_ORDER (20),
    CANCELLING (30),
    DISPENSING (40);
    
    private final int id;   // ID of the state
    
    SessionState(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}
