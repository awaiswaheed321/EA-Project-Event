package edu.miu.cs544.awais.EventManagementService.domain.exception.custom;

public class InsufficientAdminsException extends RuntimeException {
    public InsufficientAdminsException(String message) {
        super(message);
    }
}

