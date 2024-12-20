package edu.miu.cs544.awais.EventManagementService.ticket.domain;

import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.ticket.PaymentMethod;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emId;

    private Integer quantity;

    @ManyToOne
    private Event event;

    @Embedded
    private Payment payment;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Ticket() {
    }

    public Ticket(Integer quantity, Event event, PaymentMethod paymentMethod) {
        this.quantity = quantity;
        this.event = event;
        this.payment = new Payment(LocalDateTime.now(),
                Math.round((event.getTicketPrice() * quantity) * 100.0) / 100.0, paymentMethod);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + emId +
                ", quantity=" + quantity +
                ", event=" + event +
                ", payment=" + payment +
                '}';
    }

    public long getId() {
        return emId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
