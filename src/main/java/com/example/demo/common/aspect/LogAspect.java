package com.example.demo.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 匹配Controller下的所有方法
     */
    @Pointcut("execution(* com.example.demo.StudentController.*.*(..))")
    public void controllerMethod() {};

    /**
     *通知: 环绕通知, 在方法被执行前前后日志
     */
    @Around("controllerMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("方法执行了");
        Object result = joinPoint.proceed();
        System.out.println("执行完毕");
        return result;
    }
}
