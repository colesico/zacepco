package colesico.zacepco.identity.srv.service;

import jakarta.inject.Singleton;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Singleton
public class SecurityUtils {
    public static final String ALGORITHM = "SHA-256";
    public static final char SALT_DELIMITER = ':';

    private final SecureRandom SECURE_RANDOM = new SecureRandom();

    public byte[] generateSalt() {
        byte[] salt = new byte[16];
        SECURE_RANDOM.nextBytes(salt);
        return salt;
    }

    public byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(salt);
            return md.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(ALGORITHM + " algorithm not found", e);
        }
    }

    public String hashPasswordSalt(String password) {
        var salt = generateSalt();
        var passwordHash = hashPassword(password, salt);
        var encoder = Base64.getEncoder();
        return encoder.encodeToString(passwordHash) + SALT_DELIMITER + encoder.encodeToString(salt);
    }

}
