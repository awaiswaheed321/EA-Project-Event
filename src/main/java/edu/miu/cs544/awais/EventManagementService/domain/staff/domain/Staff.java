package edu.miu.cs544.awais.EventManagementService.domain.staff.domain;

import edu.miu.cs544.awais.EventManagementService.domain.user.domain.User;
import edu.miu.cs544.awais.EventManagementService.shared.UserRole;
import edu.miu.cs544.awais.EventManagementService.domain.staff.StaffRole;
import jakarta.persistence.*;

@Entity
public class Staff extends User {

    @Enumerated(EnumType.STRING)
    private StaffRole staffRole;

    public Staff() {}

    public Staff(String username, String email, String password, StaffRole staffRole) {
        super(username, email, password, UserRole.STAFF);
        this.staffRole = staffRole;
    }

    @Override
    public String toString() {
        return super.toString() + ", Staff{" +
                "staffRole=" + staffRole +
                '}';
    }

    public StaffRole getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(StaffRole staffRole) {
        this.staffRole = staffRole;
    }
}
