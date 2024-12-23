package edu.miu.cs544.awais.EventManagementService.domain.ticket;

import edu.miu.cs544.awais.EventManagementService.domain.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventId(Long eventId);
}
