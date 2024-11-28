package dev.ericms.quaoar.application.core.utils;

public enum Constants {

    MAIL_SENT_WITH_SUCCESS("Email sent with successfully!"),
    INVALID_OBJECT("Invalid attributes."),
    EMAIL_ADDRESS_NOT_FOUND("Email not found."),
    EMAIL_ADDRESS_ALREADY_REGISTERED("This e-mails already registered.");

    private final String message;

    Constants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
