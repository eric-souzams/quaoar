package dev.ericms.quaoar.infrastructure.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Arrays;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts a string to a list of strings.
     * Accepts either a JSON array string (e.g., ["a","b"]) or a comma-separated string (e.g., a,b).
     *
     * @param str the input string
     * @return a list of strings
     */
    public static List<String> stringToList(String str) {
        try {
            if (str == null || str.isBlank()) return List.of();

            if (str.trim().startsWith("[")) {
                try {
                    return objectMapper.readValue(str, new TypeReference<List<String>>() {});
                } catch (Exception e) {
                    return Arrays.stream(
                                    str.replaceAll("[\\[\\]]", "")
                                            .split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .toList();
                }
            }

            return Arrays.stream(str.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Error converting String to List", e);
        }
    }

    /**
     * Converts a list of strings to a JSON array string.
     *
     * @param list the list of strings
     * @return a JSON array string (e.g., ["a","b","c"])
     */
    public static String listToString(List<String> list) {
        try {
            if (list == null || list.isEmpty()) {
                return "[]";
            }
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("Error converting List to JSON String", e);
        }
    }

    /**
     * Converts a raw string containing emails into a valid JSON array string.
     * Accepts input like "[email1,email2]" or "email1, email2".
     *
     * @param rawString the input string with email addresses
     * @return a JSON array string of emails (e.g., ["email1","email2"])
     */
    public static String convertEmailsToJsonString(String rawString) {
        if (rawString == null || rawString.isBlank()) {
            return "[]";
        }

        List<String> emails = Arrays.stream(
                        rawString.replaceAll("[\\[\\]]", "") // Remove brackets if present
                                .split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        try {
            return objectMapper.writeValueAsString(emails);
        } catch (Exception e) {
            throw new RuntimeException("Error converting email list to JSON String", e);
        }
    }
}
