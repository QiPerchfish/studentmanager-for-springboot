package com.example.demo.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS(200,"success"),
    NOT_FOUND(404,"Not Found"),
    UNAUTHORIZED(401,"Unauthorized"),
    CREATED(201,"Created"),
    NO_CONTENT(204,"Not Content"),
    UNKNOWN(497,"Unknown"),
    BAD_GATEWAY(502,"Bad Gateway"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
    SERVICE_UNAVAILABLE(503,"Service Unavailable"),
    GATEWAY_TIMEOUT(504,"Gateway Timeout"),
    TOO_MANY_REQUESTS(294,"Too Many Requests");

    private final Integer code;
    private final String message;
    public String getMessage;
    public int getCode;

    ErrorCode(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
    
}
