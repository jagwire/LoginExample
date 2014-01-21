/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.example;

import java.util.HashMap;
import java.util.Map;
import login.example.security.BestHashGenerator;
import login.example.security.HashGenerator;
import login.example.security.PasswordValidator;
import login.example.security.TokenGenerator;

/**
 *
 * @author Ryan
 */
public class Users {

    public static UserRecord getUserFromToken(String token) {
        return SessionRepository.INSTANCE.recordsFromTokens.get(token);
    }

    public static boolean tokenIsValid(String token) {
        if (token == null || token.equals("") || token.equals(" ")) {
            return false;
        }

        return SessionRepository.INSTANCE.recordsFromTokens.containsKey(token);
    }

    public static String[] getGroupsFromUser(UserRecord user) {
        return new String[]{"User"};
    }

    public static UserRecord registerUser(String user, String password) {
        System.out.println("REGISTERING NEW USER!");
        HashGenerator generator = new BestHashGenerator();
        String storedAuthentication = generator.generateHash(password);

        System.out.println("USER: " + user + " AUTH: " + storedAuthentication);
        UserRecord record = new UserRecord(user, storedAuthentication);
        System.out.println("SANITY CHECK: 1:" + storedAuthentication + " 2:" + record.getStoredAuthentication());

        UsersDatabase.INSTANCE.addUser(record);

        return record;
    }

    public static String logUserIn(String user, String password) {
        PasswordValidator validator = new BestHashGenerator().validator();
        String storedAuth = UsersDatabase.INSTANCE.getInfoForUser(user);
        System.out.println("TRYING TO LOG IN->USER: " + user + " AND FOUND AUTH:" + storedAuth);
        if (validator.isValidUser(password, storedAuth)) {
            //generate Token

            TokenGenerator g = new TokenGenerator();
            String token = g.generateNewToken(user);

            SessionRepository.INSTANCE.recordsFromTokens.put(token, new UserRecord(user, storedAuth));
            System.out.println("GENERATED TOKEN: " + token);

            return token;

        }
        return null;

    }


    private static enum SessionRepository {
        INSTANCE;

        private Map<String, UserRecord> recordsFromTokens;

        private SessionRepository() {
            recordsFromTokens = new HashMap<>();
        }
    }

    //this will be our "database" for all intents and purposes right now
    private static enum UsersDatabase {

        INSTANCE;

        private Map<String, String> row;

        private UsersDatabase() {
            row = new HashMap<String, String>();
        }

        public String getInfoForUser(String user) {
            return row.get(user);
        }

        public void addUser(UserRecord record) {
            System.out.println("ADDING NEW USER TO DATABASE->USER: " + record.getUsername() + " PASSWORD: " + record.getStoredAuthentication());
            row.put(record.getUsername(), record.getStoredAuthentication());
        }
    }
}
