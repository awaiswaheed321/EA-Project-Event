package edu.miu.cs544.awais.EventManagementService.domain.admin.domain;

import edu.miu.cs544.awais.EventManagementService.domain.user.domain.User;
import edu.miu.cs544.awais.EventManagementService.shared.UserRole;
import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin() {}

    public Admin(String username, String email, String password) {
        super(username, email, password, UserRole.ADMIN);
    }
}
