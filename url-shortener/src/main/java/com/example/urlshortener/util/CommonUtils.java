package com.example.urlshortener.util;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class CommonUtils {

    public static String generateShortUrl(String originalUrl) {
        int hash = Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).asInt();
        // Convert hash to positive number to avoid negative values
        long positiveHash = hash & 0xffffffffL;
        // Map hash to a base62 character set (alphanumeric characters)
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortUrl = new StringBuilder();
        while (positiveHash > 0) {
            shortUrl.insert(0, alphabet.charAt((int) (positiveHash % 62)));
            positiveHash /= 62;
        }
        return shortUrl.toString();
    }
}
