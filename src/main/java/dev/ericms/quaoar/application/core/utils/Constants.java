package dev.ericms.quaoar.application.core.utils;

public enum Constants {

    MAIL_SENT_WITH_SUCCESS("Email sent with successfully!"),
    INVALID_OBJECT("Invalid attributes."),
    EMAIL_ADDRESS_NOT_FOUND("Email not found."),
    TOPIC_NOT_FOUND("Topic not found.");

    private final String message;

    Constants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
