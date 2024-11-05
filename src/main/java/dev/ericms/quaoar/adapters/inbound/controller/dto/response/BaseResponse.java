package dev.ericms.quaoar.adapters.inbound.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Data
public class BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String body;

    public BaseResponse(HttpStatus status, String message, String body) {
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

    public static ResponseEntity<BaseResponse> createResponse(HttpStatus status, String message, String body) {
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

}
