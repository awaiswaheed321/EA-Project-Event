package edu.miu.cs544.awais.EventManagementService.user;

import edu.miu.cs544.awais.EventManagementService.security.JwtHelper;
import edu.miu.cs544.awais.EventManagementService.user.domain.User;
import edu.miu.cs544.awais.EventManagementService.user.dto.LoginRequestDto;
import edu.miu.cs544.awais.EventManagementService.user.dto.LoginResponseDto;
import edu.miu.cs544.awais.EventManagementService.user.dto.RefreshTokenRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager,
                           JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword())
        );
        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        LoginResponseDto res = new LoginResponseDto(accessToken, refreshToken);
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<LoginResponseDto> refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        if (!isRefreshTokenValid) {
            throw new IllegalArgumentException("Refresh Token invalid");
        }
        String email = jwtHelper.getSubject(refreshTokenRequest.getRefreshToken());
        final String accessToken = jwtHelper.generateToken(email);
        return ResponseEntity.ok(new LoginResponseDto(accessToken, refreshTokenRequest.getRefreshToken()));
    }
}
