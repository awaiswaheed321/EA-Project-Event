package edu.miu.cs544.awais.EventManagementService.operation;

import org.aspectj.lang.JoinPoint;

public interface OperationService {
    void log(JoinPoint joinPoint);
}
