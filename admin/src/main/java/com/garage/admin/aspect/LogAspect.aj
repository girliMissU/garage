package com.garage.admin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author LIFAN
 * 2019/3/17 12:03
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.garage.admin.controller..*.*(..))")
    public void Log(){

    }

    @Before("Log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString() + ",IP : " +
                request.getRemoteAddr() + ",CLASS_METHOD : " +
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName() + ",ARGS : " +
                Arrays.toString(joinPoint.getArgs()));
    }

    //配置后置返回通知,使用在方法webLog()上注册的切入点
    @AfterReturning(returning = "object", pointcut = "Log()")
    public void doAfterReturning(Object object) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE :" + object);
    }

//    @After("Log()")
//    public void afterMethod() {
//        logger.info("after method" + new Date());
//    }
}
