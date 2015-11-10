package de.sourcepark.hubbabubba.domain;

import lombok.Data;

/**
 * The CandyError class is used to represent an error message from the HubbaBubba
 * service to the clients calling it.
 * @author smatyba
 */
@Data()
public class CandyError {
    /**
     * Each message gets a unique error code.
     */
    private int errorCode = 0;
    
    /**
     * Each message gets a unique error name.
     */
    private String errorName = "ERROR";
    
    /**
     * Each message gets an error message.
     */
    private String errorMessage = "";
    
    /**
     * Initializes a new instance of the CandyError class.
     * @param code The error code
     * @param name The name of the error
     * @param message The human-readable error message.
     */
    public CandyError(final int code, final String name, final String message) {
        this.errorCode = code;
        this.errorName = name;
        this.errorMessage = message;
    }
    
    /**
     * Initializes a new (empty) instance of the CandyError class.
     */
    public CandyError() {
        this(0, "ERROR", "");
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorName
     */
    public String getErrorName() {
        return errorName;
    }

    /**
     * @param errorName the errorName to set
     */
    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
