package edu.miu.cs544.awais.EventManagementService.user;

import edu.miu.cs544.awais.EventManagementService.user.dto.LoginRequestDto;
import edu.miu.cs544.awais.EventManagementService.user.dto.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<LoginResponseDto> login(@Valid LoginRequestDto loginRequest);
}
