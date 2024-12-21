package edu.miu.cs544.awais.EventManagementService.domain.operation;

import org.aspectj.lang.JoinPoint;

public interface OperationService {
    void log(JoinPoint joinPoint);
}
