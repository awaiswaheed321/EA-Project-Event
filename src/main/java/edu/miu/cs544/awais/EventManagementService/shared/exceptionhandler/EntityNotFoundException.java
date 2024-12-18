package edu.miu.cs544.awais.EventManagementService.shared.exceptionhandler;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
