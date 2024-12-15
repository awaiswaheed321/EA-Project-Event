package edu.miu.cs544.awais.EventManagementService.staff.dto;

import edu.miu.cs544.awais.EventManagementService.staff.StaffRole;

public class CreateStaffDTO {
    private String username;
    private String email;
    private String password;
    private StaffRole staffRole;

    public CreateStaffDTO() {}

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public StaffRole getStaffRole() {
        return staffRole;
    }
}
