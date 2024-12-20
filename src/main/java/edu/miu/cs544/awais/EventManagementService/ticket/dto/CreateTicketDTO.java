package edu.miu.cs544.awais.EventManagementService.ticket.dto;

import edu.miu.cs544.awais.EventManagementService.ticket.PaymentMethod;

public class CreateTicketDTO {
    private Integer quantity;
    private Long eventId;
    private PaymentMethod paymentMethod;
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
