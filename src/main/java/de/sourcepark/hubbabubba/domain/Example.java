package de.sourcepark.hubbabubba.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * An example domain class.
 * @author smatyba
 */
@Data()
public class Example {
    private String exampleString = "Example String";
    private int exampleInt = 42;
    private List<String> exampleList = new ArrayList<String>() {{ add("Example"); }};
    
    public void setExampleString(String exampleString){
        this.exampleString = exampleString;
    }
}
