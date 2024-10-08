package evilasio.dev.spring_starter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evilasio.dev.spring_starter.domain.dto.UserDto;
import evilasio.dev.spring_starter.domain.form.RegistrationForm;
import evilasio.dev.spring_starter.service.register.RegisterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping(path = "/register")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerToken")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> register(@RequestBody RegistrationForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.toDto(registerService.registerClient(form)));
    }
}
