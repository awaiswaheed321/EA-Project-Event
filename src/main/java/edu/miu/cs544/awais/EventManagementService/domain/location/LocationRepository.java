package edu.miu.cs544.awais.EventManagementService.domain.location;

import edu.miu.cs544.awais.EventManagementService.domain.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
