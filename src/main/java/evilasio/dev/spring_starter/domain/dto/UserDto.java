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
public class UserDto {

    private Long id;

    private Set<RoleEnum> roles;

    private Set<PermissionEnum> permissions;

    public UserDto(User user) {
        this.id = user.getId();
        this.roles = user.getRoles();
        this.permissions = user.getPermissions();
    }

    public static UserDto toDto(User user) {
        return user == null ? null : new UserDto(user);
    }
}
