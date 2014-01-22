package login.example.security;

import java.math.BigInteger;

/**
 *
 * @author Ryan
 */
public class HexUtils {

    public static byte[] fromHex(String hex) {

        byte[] binary = new byte[hex.length() / 2];

        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return binary;
    }

    public static String toHex(byte[] array) {
        BigInteger i = new BigInteger(1, array);
        String hex = i.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}
