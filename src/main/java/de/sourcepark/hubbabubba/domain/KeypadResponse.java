package de.sourcepark.hubbabubba.domain;

import lombok.Data;

/**
 *
 * @author smatyba
 */
@Data()
public class KeypadResponse {
    private String text = "OK";
    
    private String description = "HubbaBubba Response for Keypad Request";
    
    private String echo = null;
    
    public KeypadResponse() {
        this("OK", "HubbaBubba Response for Keypad Request", "<none>");
    }
    
    public KeypadResponse(final String textData, final String description, final String dataEcho) {
        this.text = textData;
        this.description = description;
        this.echo = dataEcho;
    }
}
