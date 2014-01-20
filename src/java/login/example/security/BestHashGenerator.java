/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.example.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * This is the preferred method of generating a hash.
 *
 * @author Ryan
 */
public class BestHashGenerator {

    public String generateBestHash(String password) {
        return generateBestHash(password, 1000);
    }

    public String generateBestHash(String password, int iterations) {
        try {
            char[] passwordAsCharArray = password.toCharArray();

            //generate a salt of 16 bytes
            byte[] salt = new SaltGenerator().generateSalt();

            PBEKeySpec spec = new PBEKeySpec(passwordAsCharArray, salt, iterations, 64 * 8);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();

            return iterations + ":" + HexUtils.toHex(salt) + ":" + HexUtils.toHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BestHashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(BestHashGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;

        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
