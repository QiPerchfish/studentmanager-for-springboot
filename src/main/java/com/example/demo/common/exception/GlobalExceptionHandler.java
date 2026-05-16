package com.example.demo.common.exception;

import com.example.demo.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 处理业务异常
    @ExceptionHandler(BusinssException.class)
    public Result<Void> handleBusinssException(BusinssException e) {
        log.warn("业务异常: {}", e.getMessage());
        return Result.error(e.getMessage());
    }

    // 处理参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleVaildtionException(BindException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.warn("校验参数失败: {}", message);
        return Result.error(message);
    }

    // 假如请求方法不支持
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.warn("请求方法不支持: {}", e.getMessage());
        String suppooted = String.join(", ", e.getSupportedMethods());
        return  Result.error(405, "请求方式不支持，请使用" + suppooted);
    }

    // 路径不存在
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Void> handleNotFound(NoHandlerFoundException e) {
        log.warn("请求路径不存在: {} {}", e.getHttpMethod(), e.getRequestURL());
        return Result.error(404, "资源不存在");
    }

    /**
     *其他异常
     * 同时也包括系统异常, 没有被判定的
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.warn("系统异常: ", e);
        return Result.error("系统繁忙, 请稍后再试");
    }
}
