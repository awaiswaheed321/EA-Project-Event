package edu.miu.cs544.awais.EventManagementService.operation.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emId;

    private String principle;
    private String operation;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Operation() {
    }

    public Operation(String principle, String operation) {
        this.principle = principle;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + emId +
                ", principle='" + principle + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }

    public long getId() {
        return emId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
