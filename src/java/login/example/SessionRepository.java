/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.example;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ryan
 */
public enum SessionRepository {
    INSTANCE;
    
    private Map<String, UserRecord> recordsFromTokens;

    private SessionRepository() {
        recordsFromTokens = new HashMap<String, UserRecord>();
    }
    
    public UserRecord getUserID(String token) {
        return recordsFromTokens.get(token);
    }
}
