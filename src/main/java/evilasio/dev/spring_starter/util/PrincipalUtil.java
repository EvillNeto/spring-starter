package evilasio.dev.spring_starter.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import evilasio.dev.spring_starter.domain.model.Principal;
import evilasio.dev.spring_starter.exception.StandardException;

public class PrincipalUtil {

    private PrincipalUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null && auth.getPrincipal() instanceof Principal);
    }

    public static Principal getPrincipal() {
        if (PrincipalUtil.isAuthenticated()) {
            return (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            throw new StandardException("NOT_AUTHENTICATED", "Usuario não autenticado", HttpStatus.UNAUTHORIZED);
        }
    }

    public static String getSubject() {
        return PrincipalUtil.getPrincipal().getSubject();
    }

    public static List<String> getRoles() {
        return PrincipalUtil.getPrincipal().getRoles();
    }

    public static List<String> getPermissions() {
        return PrincipalUtil.getPrincipal().getPermissions();
    }
}
