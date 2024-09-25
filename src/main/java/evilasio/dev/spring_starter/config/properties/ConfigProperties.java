package evilasio.dev.spring_starter.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("config")
@Validated
public class ConfigProperties {
    
    private @Valid Token token = new Token();

    private @Valid Origin origin = new Origin();

    private @Valid Request request = new Request();

    private @Valid Admin admin = new Admin();

    @Getter
    @Setter
    public static class Token {
        @NotBlank(message = "Token secret is not configured")
        private String secret;

        @NotNull(message = "Token expiration is not configured")
        @Min(value = 1, message = "Token expiration must be 1 or more")
        private Long expirationHours;
    }

    @Getter
    @Setter
    public static class Origin {
        private String[] allowedOrigins = { "*" };
    }

    @Getter
    @Setter
    public static class Request {
        private boolean enableSwagger = false;
        private boolean enableH2 = false;
        private String[] allowedAll = {};
        private String[] allowedGet = {};
        private String[] allowedPost = {};
        private String[] allowedPut = {};
        private String[] allowedPatch = {};
        private String[] allowedDelete = {};
    }

    @Getter
    @Setter
    public static class Admin {
        @NotBlank(message = "Admin login is not configured")
        private String login;
        
        @NotBlank(message = "Admin password is not configured")
        private String password;
    }
}
