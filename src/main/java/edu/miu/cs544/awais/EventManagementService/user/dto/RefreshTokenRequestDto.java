package edu.miu.cs544.awais.EventManagementService.user.dto;

import jakarta.validation.constraints.NotNull;

public class RefreshTokenRequestDto {
    @NotNull(message = "Refresh Token must not be null")
    private String refreshToken;

    public RefreshTokenRequestDto() {
    }

    public RefreshTokenRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
