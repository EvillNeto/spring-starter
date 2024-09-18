package evilasio.dev.spring_starter.config.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import evilasio.dev.spring_starter.config.properties.ConfigProperties;
import evilasio.dev.spring_starter.domain.model.Principal;
import evilasio.dev.spring_starter.exception.StandardError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final ConfigProperties configProperties;

    private String tokenSecret() throws IllegalArgumentException {
        String secret = configProperties.getToken().getSecret();

        if (secret == null) {
            throw new IllegalArgumentException("Token secret not configured");
        }
        return secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, IllegalArgumentException {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring("Bearer ".length());

            DecodedJWT decodedJWT = decodeToken(token, request, response);

            String subject = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

            List<String> permissions = decodedJWT.getClaim("permissions").asList(String.class);

            String type = decodedJWT.getClaim("type").asString();

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            List<SimpleGrantedAuthority> roleAuthorities = (roles != null) ? roles.stream()
                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                    .collect(Collectors.toList()) : new ArrayList<>();

            List<SimpleGrantedAuthority> permissionsAuthorities = (permissions != null) ? permissions.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()) : new ArrayList<>();

            authorities.addAll(roleAuthorities);
            authorities.addAll(permissionsAuthorities);

            Principal principal = new Principal(subject, type, roles, permissions, token);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal,
                    null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);

    }

    private DecodedJWT decodeToken(String token, HttpServletRequest request,
            HttpServletResponse response) throws IllegalArgumentException, IOException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(tokenSecret().getBytes())).build();
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = verifier.verify(token);

        } catch (JWTVerificationException e) {
            StandardError error = new StandardError(Instant.now(),
                    HttpStatus.FORBIDDEN.value(),
                    "JWT Verification Exception",
                    e.getMessage(),
                    request.getRequestURI(),
                    null);
            throwError(error, response);
        }
        return decodedJWT;
    }

    private void throwError(StandardError error, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(error.getStatus().intValue());
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, error);
        responseStream.flush();
    }
}
