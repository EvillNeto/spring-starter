package evilasio.dev.spring_starter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evilasio.dev.spring_starter.domain.form.ClientRegistrationForm;
import evilasio.dev.spring_starter.service.register.RegisterService;
import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping(path = "/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping()
    public ResponseEntity<?> registerClient(@RequestBody ClientRegistrationForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registerService.registerClient(form));
    }
}
