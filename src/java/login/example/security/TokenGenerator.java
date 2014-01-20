/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.example.security;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Adapted from Jon Kaplan's token generator in AuthSessionManagerImpl from
 * OpenWonderland
 *
 * @author Ryan
 */
public class TokenGenerator {

    public String generateNewToken(String userid) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(userid.getBytes());
            digest.update(new SaltGenerator().generateSalt(128));

            byte[] result = digest.digest();
            return Base64.encode(result);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TokenGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
