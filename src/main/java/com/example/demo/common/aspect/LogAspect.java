package com.example.demo.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


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


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.warn("无法获取请求信息");
            return joinPoint.proceed();
        }
        HttpServletRequest request = attributes.getRequest();
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        log.info("请求 {} {}", method, url);
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis();
        log.info("耗时 {} {}", costTime);
        return result;
    }
}
