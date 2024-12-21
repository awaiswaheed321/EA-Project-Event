package edu.miu.cs544.awais.EventManagementService.domain.user;

import edu.miu.cs544.awais.EventManagementService.domain.user.dto.LoginRequestDto;
import edu.miu.cs544.awais.EventManagementService.domain.user.dto.LoginResponseDto;
import edu.miu.cs544.awais.EventManagementService.domain.user.dto.RefreshTokenRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestBody @Valid RefreshTokenRequestDto refreshTokenRequest) {
        return userService.refreshToken(refreshTokenRequest);
    }
}
