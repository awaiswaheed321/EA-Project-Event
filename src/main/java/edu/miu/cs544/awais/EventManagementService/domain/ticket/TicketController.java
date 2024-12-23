package edu.miu.cs544.awais.EventManagementService.domain.ticket;

import edu.miu.cs544.awais.EventManagementService.domain.ticket.domain.Ticket;
import edu.miu.cs544.awais.EventManagementService.domain.ticket.dto.CreateTicketDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
@SecurityRequirement(name = "bearerAuth")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<Ticket> createTicket(@RequestBody @Valid CreateTicketDTO createTicketDTO) {
        return ticketService.createTicket(createTicketDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<List<Ticket>> getTicketsByEventId(@PathVariable Long eventId) {
        return ticketService.getTicketsByEventId(eventId);
    }
}
