package evilasio.dev.spring_starter.domain.form;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegistrationForm {
    
    @NotBlank
    String login;

    @NotBlank
    String password;

    @NotBlank
    String name;

    @Email
    String email;

    @CPF
    String cpf;
}
