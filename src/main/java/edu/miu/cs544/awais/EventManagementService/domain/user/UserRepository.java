package edu.miu.cs544.awais.EventManagementService.domain.user;

import edu.miu.cs544.awais.EventManagementService.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
