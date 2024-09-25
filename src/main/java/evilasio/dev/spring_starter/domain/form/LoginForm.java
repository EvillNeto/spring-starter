package evilasio.dev.spring_starter.domain.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    
    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
