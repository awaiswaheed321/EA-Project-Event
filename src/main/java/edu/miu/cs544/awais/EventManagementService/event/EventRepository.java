package edu.miu.cs544.awais.EventManagementService.event;

import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    @Query("SELECT e FROM Event e WHERE e.totalSeats >= :minSeats")
    List<Event> findEventsWithMinimumSeats(@Param("minSeats") int minSeats);

    @Query(name = "Event.findUpcoming")
    List<Event> findUpcomingEvents();

}
