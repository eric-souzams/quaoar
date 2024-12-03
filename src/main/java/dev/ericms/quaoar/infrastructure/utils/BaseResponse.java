package dev.ericms.quaoar.infrastructure.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object content;

    public BaseResponse(HttpStatus status, String message, Object content) {
        this.status = String.valueOf(status.value());
        this.message = message;
        this.content = content;
    }

    public BaseResponse(HttpStatus status, Object content) {
        this.status = String.valueOf(status.value());
        this.content = content;
    }

    public BaseResponse(HttpStatus status, String message) {
        this.status = String.valueOf(status.value());
        this.message = message;
    }

    public BaseResponse(HttpStatus status) {
        this.status = String.valueOf(status.value());
    }

    public BaseResponse() {
    }

    public static ResponseEntity<Object> createResponse(HttpStatus status, String message, Object body) {
        BaseResponse baseResponse = new BaseResponse(status, message, body);

        return ResponseEntity.status(status.value()).body(baseResponse);
    }

    public static ResponseEntity<Object> createResponse(HttpStatus status, Object body) {
        BaseResponse baseResponse = new BaseResponse(status, body);

        return ResponseEntity.status(status.value()).body(baseResponse);
    }

    public static ResponseEntity<Object> createResponse(HttpStatus status, String message) {
        BaseResponse baseResponse = new BaseResponse(status, message);

        return ResponseEntity.status(status.value()).body(baseResponse);
    }

    public static ResponseEntity<Object> createResponse(HttpStatus status) {
        BaseResponse baseResponse = new BaseResponse(status);

        return ResponseEntity.status(status.value()).body(baseResponse);
    }

    public static ResponseEntity<Object> createdResponse(String message) {
        return createResponse(HttpStatus.CREATED, message);
    }

    public static ResponseEntity<Object> createdResponse() {
        return createResponse(HttpStatus.CREATED);
    }

    public static ResponseEntity<Object> okResponse(String message) {
        return createResponse(HttpStatus.OK, message);
    }

    public static ResponseEntity<Object> okResponse() {
        return createResponse(HttpStatus.OK);
    }

    public static ResponseEntity<Object> deletedResponse() {
        return createResponse(HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<Object> deletedResponse(String message) {
        return createResponse(HttpStatus.NO_CONTENT, message);
    }

    public static ResponseEntity<Object> notFoundResponse() {
        return createResponse(HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<Object> notFoundResponse(String message) {
        return createResponse(HttpStatus.NOT_FOUND, message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
