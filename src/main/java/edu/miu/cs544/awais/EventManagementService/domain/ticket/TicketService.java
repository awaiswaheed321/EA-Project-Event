package edu.miu.cs544.awais.EventManagementService.domain.ticket;

import edu.miu.cs544.awais.EventManagementService.domain.ticket.domain.Ticket;
import edu.miu.cs544.awais.EventManagementService.domain.ticket.dto.CreateTicketDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {
    ResponseEntity<Ticket> createTicket(CreateTicketDTO createTicketDTO);

    ResponseEntity<Ticket> getTicketById(Long id);

    ResponseEntity<List<Ticket>> getAllTickets();

    ResponseEntity<List<Ticket>> getTicketsByEventId(Long eventId);
}
