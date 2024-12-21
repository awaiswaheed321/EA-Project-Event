package edu.miu.cs544.awais.EventManagementService.domain.staff.dto;

import edu.miu.cs544.awais.EventManagementService.domain.staff.StaffRole;

public class UpdateStaffDTO {
    private String username;
    private String password;
    private StaffRole staffRole;

    public UpdateStaffDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StaffRole getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(StaffRole staffRole) {
        this.staffRole = staffRole;
    }
}
