/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.state;

/**
 *
 * @author lsotoudeh
 */
public class TerminatorState implements ICandySessionState {

    @Override
    public void doAction() {
        System.out.println("TERMINATOR state");
    }
    
}
