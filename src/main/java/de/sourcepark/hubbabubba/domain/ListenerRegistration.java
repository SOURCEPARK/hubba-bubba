package de.sourcepark.hubbabubba.domain;

import lombok.Data;

/**
 * This object is used to register a listener with HubbaBubba
 *
 * @author smatyba
 */
@Data()
public class ListenerRegistration {

    private String listenerName;
    private String listenerURL;

    public ListenerRegistration(final String name, final String url) {
        this.listenerName = name;
        this.listenerURL = url;
    }

    public ListenerRegistration() {
        this("", "");
    }

    public String getListenerURL() {
        return listenerURL;
    }

    public String getListenerName() {

        return listenerName;
    }
}
