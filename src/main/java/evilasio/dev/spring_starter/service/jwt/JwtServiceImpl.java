package evilasio.dev.spring_starter.service.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import evilasio.dev.spring_starter.config.properties.ConfigProperties;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    private final ConfigProperties configProperties;

    @Override
    public String buildToken(String subject, String type, List<String> roles, List<String> permitions) {

        Instant expiresAt = Instant.now().plusMillis(Duration.ofHours(configProperties.getToken().getExpirationHours()).toMillis());

        Map<String, Object> claims = new HashMap<>();
        claims.put("type", type);
        claims.put("roles", roles);
        claims.put("permissions", permitions);

        return JWT.create()
        .withSubject(subject)
        .withExpiresAt(expiresAt)
        .withPayload(claims)
        .sign(Algorithm.HMAC512(configProperties.getToken().getSecret().getBytes()));
    }
    
}
