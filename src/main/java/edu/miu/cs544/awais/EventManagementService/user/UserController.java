package edu.miu.cs544.awais.EventManagementService.user;

import edu.miu.cs544.awais.EventManagementService.user.dto.LoginRequestDto;
import edu.miu.cs544.awais.EventManagementService.user.dto.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

}
