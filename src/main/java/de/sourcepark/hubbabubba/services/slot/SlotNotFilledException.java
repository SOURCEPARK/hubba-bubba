package de.sourcepark.hubbabubba.services.slot;

/**
 * Created by jnaperkowski on 11.12.15.
 */
public class SlotNotFilledException extends Exception {
    public SlotNotFilledException(String message) {
        super(message);
    }

    public SlotNotFilledException(String message, Exception e) {
        super(message, e);
    }
}
