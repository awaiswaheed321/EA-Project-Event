package edu.miu.cs544.awais.EventManagementService.domain.exception.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "exceptions")
public class ExceptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emId;
    private String exceptionType;
    private String principle;
    private String operation;
    @Column(length = 1000)
    private String message;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public ExceptionEntity() {
    }

    public ExceptionEntity(String exceptionType, String principle, String operation, String message) {
        this.exceptionType = exceptionType;
        this.principle = principle;
        this.operation = operation;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionEntity{" +
                "id=" + emId +
                ", exceptionType='" + exceptionType + '\'' +
                ", principle='" + principle + '\'' +
                ", operation='" + operation + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public long getId() {
        return emId;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
