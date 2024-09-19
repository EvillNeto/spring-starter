package evilasio.dev.spring_starter.domain.entity;


import java.util.Set;

import evilasio.dev.spring_starter.domain.enums.Permission;
import evilasio.dev.spring_starter.domain.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String login;

    String password;

    Set<Roles> roles;

    Set<Permission> permissions;
}
