package edu.miu.cs544.awais.EventManagementService.admin;

import edu.miu.cs544.awais.EventManagementService.user.domain.User;
import edu.miu.cs544.awais.EventManagementService.shared.enums.UserRole;
import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin() {}

    public Admin(String username, String email, String password, UserRole role) {
        super(username, email, password, role);
    }
}
