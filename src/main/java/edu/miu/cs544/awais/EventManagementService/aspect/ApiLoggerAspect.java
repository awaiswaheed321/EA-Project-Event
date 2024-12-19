package edu.miu.cs544.awais.EventManagementService.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApiLoggerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiLoggerAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void apiLoggingPointCut() {
    }


    @Around("apiLoggingPointCut()")
    public Object apiLoggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        LOGGER.info("Incoming request for {}.{} with arguments: {}", className, methodName, Arrays.toString(methodArgs));
        Object response;
        response = joinPoint.proceed();
        LOGGER.info("Response from {}.{} is: {}", className, methodName, response);
        return response;
    }

    @Pointcut("within(edu.miu.cs544.awais.EventManagementService.exception.GlobalExceptionHandler)")
    public void exceptionHandlerMethods() {
    }

    @AfterReturning(pointcut = "exceptionHandlerMethods()", returning = "response")
    public void logExceptionHandlerResponse(Object response) {
        LOGGER.info("GlobalExceptionHandler response: {}", response);
    }
}