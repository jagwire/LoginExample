/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.example.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Referenced from "Instant Java Password and Authentication Security" DO NOT
 * USE. NOT SAFE. FOR EXAMPLE PURPOSES ONLY.
 */
public class SimpleHashGenerator {

    public String getSimpleHash(String password) {
        try {
            //get a MD5 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //put the password in the digest instance
            digest.update(password.getBytes());

            //execute the digest method and get a byte array
            byte[] data = digest.digest();

            StringBuilder builder = new StringBuilder();
            //encode each byte to a hex format 
            for (int i = 0; i < data.length; i++) {
                builder.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }

            //return the built string from StringBuilder

            return builder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SimpleHashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
