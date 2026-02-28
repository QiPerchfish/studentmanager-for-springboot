package com.example.demo.common;

import ch.qos.logback.core.spi.ErrorCodes;
import lombok.Data;
import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    private Long timestamp;

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }
    //响应成功
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    private void setMessage(String success) {
    }

    private void setCode(int i) {
    }

    //成功相应
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    private void setData(T data) {

    }

    public static <T> Result<T> success(String message,T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    //失败响应
    public static <T> Result<T> error(Integer code,String message) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        return result;
    }
    //通过错误码枚举
    public static <T> Result<T> error(ErrorCode errorCode) {
        Result<T> result = new Result<>();
        result.setCode(errorCode.getCode);
        result.setMessage(errorCode.getMessage);
        return result;
    }
}
