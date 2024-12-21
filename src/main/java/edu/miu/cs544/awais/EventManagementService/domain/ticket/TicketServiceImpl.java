package edu.miu.cs544.awais.EventManagementService.domain.ticket;

import edu.miu.cs544.awais.EventManagementService.domain.customer.CustomerRepository;
import edu.miu.cs544.awais.EventManagementService.domain.customer.domain.Customer;
import edu.miu.cs544.awais.EventManagementService.domain.event.EventRepository;
import edu.miu.cs544.awais.EventManagementService.domain.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.domain.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.messaging.JmsProducer;
import edu.miu.cs544.awais.EventManagementService.messaging.Message;
import edu.miu.cs544.awais.EventManagementService.domain.ticket.domain.Ticket;
import edu.miu.cs544.awais.EventManagementService.domain.ticket.dto.CreateTicketDTO;
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
    private final JmsProducer jmsProducer;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository,
                             CustomerRepository customerRepository, JmsProducer jmsProducer) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.customerRepository = customerRepository;
        this.jmsProducer = jmsProducer;
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
        Ticket ticket = ticketRepository.save(new Ticket(createTicketDTO.getQuantity(), event,
                createTicketDTO.getPaymentMethod()));
        customer.addTicket(ticket);
        event.setAvailableSeats(event.getAvailableSeats() - createTicketDTO.getQuantity());
        customerRepository.save(customer);
        eventRepository.save(event);
        jmsProducer.sendMessage(new Message(event.getId(), event.getName(), ticket.getId(), customer.getId(),
                customer.getEmail()));
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
    @Transactional
    public ResponseEntity<List<Ticket>> getTicketsByEventId(Long eventId) {
        Event event = findEventById(eventId);
        return ResponseEntity.ok(ticketRepository.findByEventId(event.getId()));
    }

    private Event findEventById(Long id) {
        return eventRepository.findByIdWithLock(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found: " + id));
    }
}
