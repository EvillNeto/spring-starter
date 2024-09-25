package evilasio.dev.spring_starter.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evilasio.dev.spring_starter.util.PrincipalUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/hello")
@SecurityRequirement(name = "bearerToken")
public class HelloController {

    @GetMapping()
    public List<String> helloRoles() {
        return PrincipalUtil.getRoles();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/client")
    @PreAuthorize("hasRole('CLIENT')")
    public String helloClient() {
        return "Hello Client";
    }

}
