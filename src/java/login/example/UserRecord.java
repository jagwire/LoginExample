package login.example;

/**
 * Data Transfer Object from "database".
 *
 * @author Ryan
 */
public class UserRecord {
    private String username;
    private String storedAuthentication;

    public UserRecord(String username, String storedAuthentication) {
        this.username = username;
        this.storedAuthentication = storedAuthentication;
    }

    public String getUsername() {
        
        return username;
    }

    public String getStoredAuthentication() {
        return storedAuthentication;
    }
    
}
