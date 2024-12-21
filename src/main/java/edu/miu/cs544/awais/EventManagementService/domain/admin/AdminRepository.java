package edu.miu.cs544.awais.EventManagementService.domain.admin;

import edu.miu.cs544.awais.EventManagementService.domain.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);
}
