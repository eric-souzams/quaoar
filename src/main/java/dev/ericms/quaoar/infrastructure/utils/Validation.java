package dev.ericms.quaoar.infrastructure.utils;

import java.util.Collection;

public class Validation {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isPositive(Integer number) {
        return number != null && number > 0;
    }

    public static boolean isNegative(Integer number) {
        return number != null && number < 0;
    }

    public static boolean isAlpha(String str) {
        return str != null && str.matches("^[a-zA-Z]+$");
    }

    public static boolean isAlphaNumeric(String str) {
        return str != null && str.matches("^[a-zA-Z0-9]+$");
    }

}

