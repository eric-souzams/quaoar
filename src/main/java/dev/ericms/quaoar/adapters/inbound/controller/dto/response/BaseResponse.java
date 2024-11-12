package dev.ericms.quaoar.adapters.inbound.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Class<?> body;

    public BaseResponse(HttpStatus status, String message, Class<?> body) {
        this.status = String.valueOf(status.value());
        this.message = message;
        this.body = body;
    }

    public BaseResponse(HttpStatus status, String message) {
        this.status = String.valueOf(status.value());
        this.message = message;
    }

    public BaseResponse(HttpStatus status) {
        this.status = String.valueOf(status.value());
    }

    public static ResponseEntity<BaseResponse> createResponse(HttpStatus status, String message, Class<?> body) {
        BaseResponse baseResponse = new BaseResponse(status, message, body);

        return ResponseEntity.status(status.value()).body(baseResponse);
    }

    public static ResponseEntity<BaseResponse> createResponse(HttpStatus status, String message) {
        BaseResponse baseResponse = new BaseResponse(status, message);

        return ResponseEntity.status(status.value()).body(baseResponse);
    }

    public static ResponseEntity<BaseResponse> createResponse(HttpStatus status) {
        BaseResponse baseResponse = new BaseResponse(status);

        return ResponseEntity.status(status.value()).body(baseResponse);
    }

    public static ResponseEntity<BaseResponse> createdResponse(String message) {
        return createResponse(HttpStatus.CREATED, message);
    }

    public static ResponseEntity<BaseResponse> createdResponse() {
        return createResponse(HttpStatus.CREATED);
    }

    public static ResponseEntity<BaseResponse> okResponse(String message) {
        return createResponse(HttpStatus.OK, message);
    }

    public static ResponseEntity<BaseResponse> okResponse() {
        return createResponse(HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> deletedResponse() {
        return createResponse(HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<BaseResponse> deletedResponse(String message) {
        return createResponse(HttpStatus.NO_CONTENT, message);
    }

    public static ResponseEntity<BaseResponse> notFoundResponse() {
        return createResponse(HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<BaseResponse> notFoundResponse(String message) {
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

    public Class<?> getBody() {
        return body;
    }

    public void setBody(Class<?> body) {
        this.body = body;
    }
}
