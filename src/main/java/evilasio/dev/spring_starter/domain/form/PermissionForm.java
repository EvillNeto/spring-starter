package evilasio.dev.spring_starter.domain.form;

import java.util.Set;

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
public class PermissionForm {
    
    private Set<RoleEnum> roles;

    private Set<PermissionEnum> permissions;
}
