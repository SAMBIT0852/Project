package com.cosmos.vcs.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HashUtil {
    
    private static final String HASH_ALGORITHM = "SHA-256";
    
    public static String calculateFileHash(Path filePath) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] fileBytes = Files.readAllBytes(filePath);
            byte[] hashBytes = digest.digest(fileBytes);
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
    
    public static String calculateStringHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hashBytes = digest.digest(input.getBytes());
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
    
    public static String generateCommitId(String message, LocalDateTime timestamp, String parentId) {
        String input = message + timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + 
                      (parentId != null ? parentId : "");
        return calculateStringHash(input).substring(0, 40);
    }
    
    public static String generateShortHash(String fullHash) {
        return fullHash != null && fullHash.length() >= 8 ? fullHash.substring(0, 8) : fullHash;
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}