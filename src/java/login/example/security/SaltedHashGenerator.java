/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.example.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A salt is an array of random bytes used to "prime" a message digest in order
 * to generate a more secure password.
 *
 * @author Ryan
 */
public class SaltedHashGenerator {


    public byte[] getSaltedHash(String password, byte[] salt) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            //"prime" the digest with the salt
            digest.update(salt);

            byte[] data = digest.digest(password.getBytes());
            digest.reset();

            return data;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SaltedHashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
