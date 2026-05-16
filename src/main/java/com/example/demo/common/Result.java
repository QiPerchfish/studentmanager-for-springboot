package com.example.demo.common;

import java.time.LocalDateTime;

public class Result<T> {

    private Integer code;
    private String message;
    private T data;
    private Long timestamp;

    // 时间戳
    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.data = data;
        result.code = 500;
        result.message = "success";
        return result;
    }
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.message = message;
        result.code = 500;
        return result;
    }
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        return result;
    }

    // getter和setter
    public Integer getCode() {
        return code;
    }
    public void setCode() {
        this.code = code;
    }
    public T getData() {
        return data;
    }
    public void setData() {
        this.data = data;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp() {
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage() {
        this.message = message;
    }
}
