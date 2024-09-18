package evilasio.dev.spring_starter.service.jwt;

import java.util.List;

public interface JwtService {

    public String buildToken(String subject, String type, List<String> roles, List<String> permitions);
}
