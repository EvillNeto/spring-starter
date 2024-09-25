package evilasio.dev.spring_starter.domain.dto;

import java.util.Set;

import evilasio.dev.spring_starter.domain.entity.User;
import evilasio.dev.spring_starter.domain.enums.PermissionEnum;
import evilasio.dev.spring_starter.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private Long userId;

    private Set<RoleEnum> roles;

    private Set<PermissionEnum> permissions;

    private String token;

    public TokenDto(User user, String token) {
        this.userId = user.getId();
        this.roles = user.getRoles();
        this.permissions = user.getPermissions();
        this.token = token;
    }
}
