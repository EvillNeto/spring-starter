package evilasio.dev.spring_starter.service.jwt;

import java.util.Set;

import evilasio.dev.spring_starter.domain.enums.PermissionEnum;
import evilasio.dev.spring_starter.domain.enums.RoleEnum;

public interface JwtService {

    public String buildToken(String subject, Set<RoleEnum> roles, Set<PermissionEnum> permitions);
}
