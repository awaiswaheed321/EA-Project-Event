package edu.miu.cs544.awais.EventManagementService.aspect;

import edu.miu.cs544.awais.EventManagementService.exception.ExceptionsEntityService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionsAspect {
    private final ExceptionsEntityService exceptionsEntityService;

    public ExceptionsAspect(ExceptionsEntityService exceptionsEntityService) {
        this.exceptionsEntityService = exceptionsEntityService;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerAnyMethodPointcut() {}

    @AfterThrowing(value = "controllerAnyMethodPointcut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        this.exceptionsEntityService.log(joinPoint, exception);
    }
}
