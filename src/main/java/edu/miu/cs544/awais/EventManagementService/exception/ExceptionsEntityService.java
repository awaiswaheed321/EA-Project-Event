package edu.miu.cs544.awais.EventManagementService.exception;

import org.aspectj.lang.JoinPoint;

public interface ExceptionsEntityService {
    void log(JoinPoint joinPoint, Exception exception);
}
