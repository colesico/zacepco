package colesico.zacepco.identity.srv.assist;

import jakarta.inject.Singleton;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Singleton
public class HashUtils {

    public static final String ALGORITHM = "SHA-256";

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static byte[] salt() {
        byte[] salt = new byte[16];
        SECURE_RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] textToHash(String text, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(salt);
            return md.digest(text.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(ALGORITHM + " algorithm not found", e);
        }
    }

    public static byte[] textToHash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            return md.digest(text.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(ALGORITHM + " algorithm not found", e);
        }
    }

    public static String textToHashStr(String text) {
        return Base64.getEncoder().encodeToString(textToHash(text));
    }

    public static String[] textToHashSalt(String text) {
        var salt = salt();
        var hash = textToHash(text, salt);
        var encoder = Base64.getEncoder();
        return new String[]{encoder.encodeToString(hash), encoder.encodeToString(salt)};
    }

}
