package edu.miu.cs544.awais.EventManagementService.domain.ticket.domain;

import edu.miu.cs544.awais.EventManagementService.domain.ticket.PaymentMethod;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

@Embeddable
public class Payment {
    private LocalDateTime date;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    public Payment() {
    }

    public Payment(LocalDateTime date, Double amount, PaymentMethod paymentMethod) {
        this.date = date;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "date=" + date +
                ", amount=" + amount +
                ", paymentMethod=" + paymentMethod +
                '}';
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
