package edu.miu.cs544.awais.EventManagementService.ticket;

import edu.miu.cs544.awais.EventManagementService.ticket.domain.Ticket;
import edu.miu.cs544.awais.EventManagementService.ticket.dto.CreateTicketDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {
    ResponseEntity<Ticket> createTicket(CreateTicketDTO createTicketDTO);

    ResponseEntity<Ticket> getTicketById(Long id);

    ResponseEntity<List<Ticket>> getAllTickets();

    ResponseEntity<List<Ticket>> getTicketsByEventId(Long eventId);
}
