package edu.miu.cs544.awais.EventManagementService.aspect;

import edu.miu.cs544.awais.EventManagementService.domain.operation.OperationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OperationAspect {
    private final OperationService operationLoggerService;

    public OperationAspect(OperationService operationLoggerService) {
        this.operationLoggerService = operationLoggerService;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerAnyMethodPointcut() {
    }

    @Before("controllerAnyMethodPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        this.operationLoggerService.log(joinPoint);
    }
}
