package edu.miu.cs544.awais.EventManagementService.staff.dto;

import edu.miu.cs544.awais.EventManagementService.staff.StaffRole;
import jakarta.validation.constraints.NotEmpty;

public class CreateStaffDTO {
    @NotEmpty(message = "Staff Name must not be empty")
    private String username;
    @NotEmpty(message = "Staff Email must not be empty")
    private String email;
    @NotEmpty(message = "Staff password must not be empty")
    private String password;
    @NotEmpty(message = "Staff Role must not be empty")
    private StaffRole staffRole;

    public CreateStaffDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
