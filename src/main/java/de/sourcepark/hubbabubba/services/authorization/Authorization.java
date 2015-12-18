/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.services.authorization;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lsotoudeh
 */
public class Authorization {
    
    /**
     * Logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(Authorization.class);
    
    
 
    private static Map<String, User> userRegistry;

    // the Authorization instance
    private static Authorization instance;
    

    // Singleton constructor
    private Authorization() {
    }

    /**
     * Constructor providing a Singleton Authorization
     * @param authorizationFilePath the path to the file containing the authorized user information
     * @return the Singleton Authorization
     * @throws FileNotFoundException if the authorizationFile containing the user information was not found
     */
    public static Authorization getInstance(String authorizationFilePath) throws FileNotFoundException, IOException {
        if (Authorization.instance == null) {
            Authorization.instance = new Authorization();
        }
        populateUserRegistry(authorizationFilePath);
        return Authorization.instance;
    }
    
    private static void populateUserRegistry(String authorizationFilePath) throws FileNotFoundException, IOException {
        CSVReader reader = new CSVReader(new FileReader(authorizationFilePath), ';', '"');
        userRegistry = new HashMap<>();
        User tmpUser;
        String[] split;
        while ((split = reader.readNext()) != null) {
            tmpUser = new User();
            tmpUser.setCardId(split[0].toUpperCase());
            if (split.length>1) {
                tmpUser.setMaintenanceStaff(Boolean.parseBoolean(split[1]));
            } 
            if (split.length>2) {
                tmpUser.setNickname(split[2]);
            } 
            if (split.length>3) {
                tmpUser.setSalutation(split[3]);
            }
            userRegistry.put(tmpUser.getCardId(), tmpUser);
        }
    }
    
    /**
     * checks, if given User is authorized to order something
     * @param cardId the cardId of the User to authorize
     * @return the User, if a User fits to the cardId, else null
     */
    public static User authorize(final String cardId) {
        return userRegistry.get(cardId.toUpperCase());
    }
    
    public static void reloadUserRegistry(String authorizationFilePath) throws FileNotFoundException, IOException {
        populateUserRegistry(authorizationFilePath);
    }
            
}
