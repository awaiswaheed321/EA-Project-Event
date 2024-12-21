package edu.miu.cs544.awais.EventManagementService.domain.event;

import edu.miu.cs544.awais.EventManagementService.domain.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.domain.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.domain.location.domain.Location;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    @Query("SELECT e FROM Event e WHERE e.totalSeats >= :minSeats")
    List<Event> findEventsWithMinimumSeats(@Param("minSeats") int minSeats);

    @Query(name = "Event.findUpcoming")
    List<Event> findUpcomingEvents();

    long countByCategory(Category category);

    long countByLocation(Location location);

    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @Query("SELECT e FROM Event e WHERE e.emId = :id")
    Optional<Event> findByIdWithLock(Long id);
}
