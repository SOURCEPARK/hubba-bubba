package de.sourcepark.hubbabubba.domain;

import lombok.Data;

/**
 *
 * @author smatyba
 */
@Data()
public class CandyError {
    private int errorCode = 0;
    private String errorName = "ERROR";
    private String errorMessage = "";
}
