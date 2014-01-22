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
public class BestHashGenerator implements HashGenerator {

    @Override
    public String generateHash(String password) {
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

    public PasswordValidator validator() {
        return new PasswordValidator() {
            @Override
            public boolean isValidUser(String password, String storedPassword) {
                try {
                    char[] passwordAsCharArray = password.toCharArray();

                    String[] params = storedPassword.split(":");
                    int iterations = Integer.parseInt(params[0]);
                    byte[] salt = HexUtils.fromHex(params[1]);
                    byte[] hash = HexUtils.fromHex(params[2]);

                    PBEKeySpec spec = new PBEKeySpec(passwordAsCharArray, salt, iterations, 64 * 8);
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

                    byte[] testHash = factory.generateSecret(spec).getEncoded();
                    return slowEquals(hash, testHash);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(BestHashGenerator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(BestHashGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }
        };
    }

    public static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;

        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
