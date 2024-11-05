package dev.ericms.quaoar.adapters.inbound.controller.utils;

public enum Constants {

    MAIL_SENT_WITH_SUCCESS("Email sent with successfully!");

    private final String message;

    Constants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
