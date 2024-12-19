package edu.miu.cs544.awais.EventManagementService.admin.dto;

import jakarta.validation.constraints.NotEmpty;

public class CreateAdminDTO {
    @NotEmpty(message = "Staff Name must not be empty")
    private String username;
    @NotEmpty(message = "Staff Email must not be empty")
    private String email;
    @NotEmpty(message = "Staff password must not be empty")
    private String password;

    public CreateAdminDTO() {
    }

    public CreateAdminDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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
}