package com.example.demo.common.exception;

import com.example.demo.common.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

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

    /**
     * 学生未找到异常处理方法
     */
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Void> handleStudentNotFoundException(StudentNotFoundException e) {
        log.warn("该学生不存在: {}", e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(StudentNoAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Result<Void> handleStudentNoAlreadyExistsException(StudentNoAlreadyExistsException e) {
        log.warn("学号已经存在: {}", e.getMessage());
        return Result.error(e.getMessage());
    }

    // -----------业务异常到此------

    /**
     * 关于状态码的异常
     * @param e
     * @return
     */

    // 假如请求方法不支持
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<Void> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.warn("请求方法不支持: {}", e.getMessage());
        String suppooted = String.join(", ", e.getSupportedMethods());
        return  Result.error(405, "请求方式不支持，请使用" + suppooted);
    }

    /**
     * 缺少重要的参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleMissingParams(MissingServletRequestParameterException e) {
        log.warn("请求缺少核心参数: {}", e.getMessage());
        return Result.error(400,"请求缺少重要参数:" + e.getParameterName());
    }

    /**
     * 参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        log.warn("参数类型无法匹配: {}", e.getMessage());
        return Result.error(400, "请求参数无法匹配, 检查参数是否能够匹配类型");
    }

    /**
     * 字段校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleNotValidArgument(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.warn("字段校验失败: {}", e.getMessage());
        return Result.error(400, "字段无法被正常校验");
    }

    /**
     * JSON文件格式错误处理
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleNotReadable(HttpMessageNotReadableException e) {
        log.warn("请求格式错误: {}", e.getMessage());
        return Result.error(400, "请求本体格式出错, 检查JSON来检查原因");
    }

    // 路径不存在
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Void> handleNotFound(NoHandlerFoundException e) {
        log.warn("请求路径不存在: {} {}", e.getHttpMethod(), e.getRequestURL());
        return Result.error(404, "资源不存在");
    }

    /**
     * 数据库操作异常处理
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Result<Void> handleDatabaseConflict(DataIntegrityViolationException e) {
        log.warn("数据库操作冲突: {}", e.getMessage());
        return Result.error(409, "数据库操作冲突, 您可能需要查看您学生们的学号是否冲突");
    }

    /**
     * 无权限访问
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<Void> handleAccessDenied(AccessDeniedException e) {
        log.warn("无权限访问: {}", e.getMessage());
        return Result.error(403, "您暂时没有此处访问权限");
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
