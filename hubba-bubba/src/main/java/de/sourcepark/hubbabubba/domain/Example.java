package de.sourcepark.hubbabubba.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author smatyba
 */
@Data()
public class Example {
    private String exampleString = "Example String";
    private int exampleInt = 42;
    private List<String> exampleList = new ArrayList<String>() {{ add("Example"); }};
    
//    public String getExampleString() {
//        return this.exampleString;
//    }
//    
//    public void setExampleString(final String value) {
//        this.exampleString = value;
//    }
    
}
