package edu.miu.cs544.awais.EventManagementService.exception;

import edu.miu.cs544.awais.EventManagementService.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.exception.custom.InsufficientAdminsException;
import edu.miu.cs544.awais.EventManagementService.exception.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        LOGGER.error(GlobalExceptionHandler.class.getName(), ex.getMessage(), ex);
        ErrorDTO errorResponse = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(EntityNotFoundException ex, WebRequest request) {
        LOGGER.error(GlobalExceptionHandler.class.getName(), ex.getMessage(), ex);
        ErrorDTO errorResponse = new ErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        LOGGER.error(GlobalExceptionHandler.class.getName(), ex.getMessage(), ex);
        ErrorDTO errorResponse = new ErrorDTO(
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid username or password"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorDTO> handleAuthorizationDeniedException(AuthorizationDeniedException ex,
                                                                       WebRequest request) {
        LOGGER.error(GlobalExceptionHandler.class.getName(), ex.getMessage(), ex);
        ErrorDTO errorResponse = new ErrorDTO(
                HttpStatus.FORBIDDEN.value(),
                "You do not have permission to access this resource"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InsufficientAdminsException.class)
    public ResponseEntity<ErrorDTO> handleInsufficientAdminsException(InsufficientAdminsException ex,
                                                                      WebRequest request) {
        LOGGER.error(GlobalExceptionHandler.class.getName(), ex.getMessage(), ex);
        ErrorDTO errorResponse = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception ex, WebRequest request) {
        LOGGER.error(GlobalExceptionHandler.class.getName(), ex.getMessage(), ex);
        ErrorDTO errorResponse = new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
