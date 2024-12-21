package edu.miu.cs544.awais.EventManagementService.domain.staff;

import edu.miu.cs544.awais.EventManagementService.domain.staff.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    boolean existsByEmail(String email);
}
