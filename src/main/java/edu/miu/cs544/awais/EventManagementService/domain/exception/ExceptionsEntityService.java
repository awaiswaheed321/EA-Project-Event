package edu.miu.cs544.awais.EventManagementService.domain.exception;

import org.aspectj.lang.JoinPoint;

public interface ExceptionsEntityService {
    void log(JoinPoint joinPoint, Exception exception);
}
