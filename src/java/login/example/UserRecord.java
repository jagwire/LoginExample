/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.example;

/**
 *
 * @author ryan
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
