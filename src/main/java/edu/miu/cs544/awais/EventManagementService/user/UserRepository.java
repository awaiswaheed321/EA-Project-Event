package edu.miu.cs544.awais.EventManagementService.user;

import edu.miu.cs544.awais.EventManagementService.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
