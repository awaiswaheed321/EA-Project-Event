package edu.miu.cs544.awais.EventManagementService.messaging;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private Long eventId;
    private String eventName;
    private Long ticketId;
    private Long customerId;
    private String customerEmail;

    public Message() {
    }

    public Message(Long eventId, String eventName, Long ticketId, Long customerId, String customerEmail) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.customerEmail = customerEmail;
    }
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("eventId", this.eventId);
        map.put("eventName", this.eventName);
        map.put("ticketId", this.ticketId);
        map.put("customerId", this.customerId);
        map.put("customerEmail", this.customerEmail);
        return map;
    }


    @Override
    public String toString() {
        return "Message{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", ticketId=" + ticketId +
                ", customerId=" + customerId +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
