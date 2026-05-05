package com.example.demo.common.exception;

public class BusinssException extends RuntimeException {
    private String code;

    public BusinssException(String message) {
        super(message);
        this.code = "500";
    }
    public BusinssException(String code, String message) {
        super(message);
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
