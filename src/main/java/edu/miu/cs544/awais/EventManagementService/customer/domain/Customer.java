package edu.miu.cs544.awais.EventManagementService.customer.domain;

import edu.miu.cs544.awais.EventManagementService.shared.UserRole;
import edu.miu.cs544.awais.EventManagementService.ticket.domain.Ticket;
import edu.miu.cs544.awais.EventManagementService.user.domain.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {
    private String phoneNumber;
    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    List<Ticket> tickets = new ArrayList<>();

    public Customer() {
    }

    public Customer(String username, String email, String password, String phoneNumber, String street,
                    String city, String state, String zip) {
        super(username, email, password, UserRole.CUSTOMER);
        this.phoneNumber = phoneNumber;
        this.address = new Address(street, city, state, zip);
    }

    @Override
    public String toString() {
        return super.toString() + ", Customer{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", tickets=" + tickets +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
