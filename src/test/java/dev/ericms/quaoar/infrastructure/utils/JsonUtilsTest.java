package dev.ericms.quaoar.infrastructure.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {

    @Test
    void testStringToList_withJsonArray() {
        String input = "[\"email1@test.com\", \"email2@test.com\"]";
        List<String> result = JsonUtils.stringToList(input);

        assertEquals(2, result.size());
        assertTrue(result.contains("email1@test.com"));
        assertTrue(result.contains("email2@test.com"));
    }

    @Test
    void testStringToList_withCommaSeparated() {
        String input = "email1@test.com, email2@test.com";
        List<String> result = JsonUtils.stringToList(input);

        assertEquals(2, result.size());
        assertEquals(List.of("email1@test.com", "email2@test.com"), result);
    }

    @Test
    void testStringToList_withEmptyString() {
        String input = "";
        List<String> result = JsonUtils.stringToList(input);

        assertTrue(result.isEmpty());
    }

    @Test
    void testListToString() {
        List<String> input = List.of("email1@test.com", "email2@test.com");
        String result = JsonUtils.listToString(input);

        assertEquals("[\"email1@test.com\",\"email2@test.com\"]", result);
    }

    @Test
    void testListToString_withEmptyList() {
        List<String> input = List.of();
        String result = JsonUtils.listToString(input);

        assertEquals("[]", result);
    }

    @Test
    void testConvertEmailsToJsonString_withBrackets() {
        String input = "[email1@test.com, email2@test.com]";
        String result = JsonUtils.convertEmailsToJsonString(input);

        assertEquals("[\"email1@test.com\",\"email2@test.com\"]", result);
    }

    @Test
    void testConvertEmailsToJsonString_withoutBrackets() {
        String input = "email1@test.com, email2@test.com";
        String result = JsonUtils.convertEmailsToJsonString(input);

        assertEquals("[\"email1@test.com\",\"email2@test.com\"]", result);
    }

    @Test
    void testConvertEmailsToJsonString_withEmptyString() {
        String input = "";
        String result = JsonUtils.convertEmailsToJsonString(input);

        assertEquals("[]", result);
    }
}
