package evilasio.dev.spring_starter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evilasio.dev.spring_starter.domain.dto.UserDto;
import evilasio.dev.spring_starter.domain.form.PermissionForm;
import evilasio.dev.spring_starter.service.permission.PermissionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping(path = "/permission")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerToken")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> register(@PathVariable Long id, @RequestBody PermissionForm form) {
        return ResponseEntity.status(HttpStatus.OK).body(UserDto.toDto(permissionService.changePermissions(id, form)));
    }
}
