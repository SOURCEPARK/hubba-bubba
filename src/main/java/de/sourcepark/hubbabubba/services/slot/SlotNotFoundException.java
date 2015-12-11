package de.sourcepark.hubbabubba.services.slot;

/**
 * Created by jnaperkowski on 11.12.15.
 */
public class SlotNotFoundException extends Exception {
    public SlotNotFoundException(String message) {
        super(message);
    }

    public SlotNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
