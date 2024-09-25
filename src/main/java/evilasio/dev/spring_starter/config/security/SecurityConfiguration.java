package evilasio.dev.spring_starter.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import evilasio.dev.spring_starter.config.filter.JwtAuthenticationFilter;
import evilasio.dev.spring_starter.config.properties.ConfigProperties;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final ConfigProperties configProperties;

    private static final String[] SWAGGER_PATHS = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/configuration/ui",
            "/configuration/security",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config"
    };

    private static final String[] H2_PATHS = {
            "/h2-console",
            "/h2-console/**"
    };

    private String[] swaggerPath() {
        if (configProperties.getRequest().isEnableSwagger()) {
            return SWAGGER_PATHS;
        } else {
            return new String[] {};
        }
    }

    private String[] h2Path() {
        if (configProperties.getRequest().isEnableH2()) {
            return H2_PATHS;
        } else {
            return new String[] {};
        }
    }

    private String[] allowedAll() {
        return configProperties.getRequest().getAllowedAll();
    }

    private String[] allowedGet() {
        return configProperties.getRequest().getAllowedGet();
    }

    private String[] allowedPost() {
        return configProperties.getRequest().getAllowedPost();
    }

    private String[] allowedPut() {
        return configProperties.getRequest().getAllowedPut();
    }

    private String[] allowedPatch() {
        return configProperties.getRequest().getAllowedPatch();
    }

    private String[] allowedDelete() {
        return configProperties.getRequest().getAllowedDelete();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()
                        .requestMatchers(swaggerPath())
                        .permitAll()
                        .requestMatchers(allowedAll())
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, allowedGet())
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, allowedPost())
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, allowedPut())
                        .permitAll()
                        .requestMatchers(HttpMethod.PATCH, allowedPatch())
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, allowedDelete())
                        .permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(h2Path());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
