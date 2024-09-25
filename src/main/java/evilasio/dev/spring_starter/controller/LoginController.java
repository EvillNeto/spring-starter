package evilasio.dev.spring_starter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evilasio.dev.spring_starter.domain.dto.TokenDto;
import evilasio.dev.spring_starter.domain.form.LoginForm;
import evilasio.dev.spring_starter.service.login.LoginService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/login")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerToken")
public class LoginController {

    private final LoginService loginService;


    @PostMapping()
    public ResponseEntity<TokenDto> postMethodName(@RequestBody LoginForm form) {
        
        return ResponseEntity.ok(loginService.login(form));
    }
    
}
