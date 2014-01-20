/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.example.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generate a random numbers in an array of fixed length;, called a salt.
 *
 * Salts should always be atleast 16 bytes in length;
 *
 * @author Ryan
 */
public class SaltGenerator {

    public byte[] generateSalt() {
        return generateSalt(16);
    }

    public byte[] generateSalt(int length) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            //128-bits
            byte[] salt = new byte[length];

            //128 random bits are now in the salt.
            secureRandom.nextBytes(salt);


            return salt;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SaltedHashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
