package dev.ericms.quaoar.application.core.utils;

public enum Constants {

    MAIL_SENT_WITH_SUCCESS("Email sent with successfully!"),
    INVALID_OBJECT("Invalid attributes."),
    INVALID_MESSAGE("Invalid message."),
    EMAIL_ADDRESS_NOT_FOUND("Email not found."),
    TOPIC_NOT_FOUND("Topic not found."),
    TEMPLATE_NOT_FOUND("Template not found."),
    TEMPLATE_OR_PARAMS_INVALID("Template or parameters are invalid."),
    TEMPLATE_CREATED_WITH_SUCCESS("Template created with success."),
    TEMPLATE_UPDATED_WITH_SUCCESS("Template updated with success."),
    TEMPLATE_DELETED_WITH_SUCCESS("Template deleted with success."),
    MESSAGE_NOT_FOUND("Message not found."),
    MESSAGE_DELETED_WITH_SUCCESS("Message deleted with success.");

    private final String message;

    Constants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
