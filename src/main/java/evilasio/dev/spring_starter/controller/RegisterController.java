package evilasio.dev.spring_starter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evilasio.dev.spring_starter.domain.dto.ClientDto;
import evilasio.dev.spring_starter.domain.form.ClientRegistrationForm;
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
    public ResponseEntity<ClientDto> registerClient(@RequestBody ClientRegistrationForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientDto.toDto(registerService.registerClient(form)));
    }
}
