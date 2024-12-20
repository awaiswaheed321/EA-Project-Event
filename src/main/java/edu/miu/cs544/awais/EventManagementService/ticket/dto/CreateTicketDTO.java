package edu.miu.cs544.awais.EventManagementService.ticket.dto;

import edu.miu.cs544.awais.EventManagementService.ticket.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateTicketDTO {
    @NotNull(message = "Quantity cannot be null.")
    @Min(value = 1, message = "Quantity must be greater than or equal to 1.")
    private Integer quantity;

    @NotNull(message = "Event ID cannot be null.")
    private Long eventId;

    @NotNull(message = "Payment method cannot be null.")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Customer ID cannot be null.")
    private Long customerId;

    public CreateTicketDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
