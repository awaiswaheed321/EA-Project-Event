package edu.miu.cs544.awais.EventManagementService.ticket;

import edu.miu.cs544.awais.EventManagementService.customer.CustomerRepository;
import edu.miu.cs544.awais.EventManagementService.customer.domain.Customer;
import edu.miu.cs544.awais.EventManagementService.event.EventRepository;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.ticket.domain.Ticket;
import edu.miu.cs544.awais.EventManagementService.ticket.dto.CreateTicketDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository,
                             CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Ticket> createTicket(CreateTicketDTO createTicketDTO) {
        Customer customer = customerRepository.findById(createTicketDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: " + createTicketDTO.getCustomerId()));
        Event event = findEventById(createTicketDTO.getEventId());
        if (event.getAvailableSeats() < createTicketDTO.getQuantity()) {
            throw new IllegalArgumentException("Not enough available seats for this event.");
        }
        Ticket ticket = new Ticket(createTicketDTO.getQuantity(), event, createTicketDTO.getPaymentMethod());
        customer.addTicket(ticket);
        event.setAvailableSeats(event.getAvailableSeats() - createTicketDTO.getQuantity());
        customerRepository.save(customer);
        eventRepository.save(event);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Ticket> getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        return ResponseEntity.ok(ticket);
    }

    @Override
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Ticket>> getTicketsByEventId(Long eventId) {
        Event event = findEventById(eventId);
        return ResponseEntity.ok(ticketRepository.findByEventId(event.getId()));
    }

    private Event findEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found: " + id));
    }
}
