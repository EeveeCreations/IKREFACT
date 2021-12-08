package nl.hsleiden.svdj8.services;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class HashService {

    public HashService() {

    }

    public static String hashPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        char[] passwordChar = password.toCharArray();
        int iterations = 10000;

        PBEKeySpec keySpec = new PBEKeySpec(passwordChar, salt, iterations, 512);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
        return iterations + ":" + makeHex(salt) + ":" + makeHex(hash);
    }

    public static boolean comparePassword(String password, String databasePassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] splitted = databasePassword.split(":");
        int iterations = Integer.parseInt(splitted[0]);
        byte[] salt = undoHex(splitted[1]);
        byte[] hash = undoHex(splitted[2]);

        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] explorerHash = secretKeyFactory.generateSecret(keySpec).getEncoded();

        int diff = hash.length  ^ explorerHash.length;
        for(int i = 0; i < hash.length && i < explorerHash.length; i++) {
            diff |= hash[i] ^ explorerHash[i];
        }
        return diff == 0;
    }

    private static String makeHex(byte[] bytes) {
        BigInteger bigInteger = new BigInteger(1, bytes);
        String hex = bigInteger.toString(16);
        int paddingLength = (bytes.length * 2) - hex.length();
        if(paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] undoHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

}