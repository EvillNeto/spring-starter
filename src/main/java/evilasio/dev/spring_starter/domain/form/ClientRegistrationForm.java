package evilasio.dev.spring_starter.domain.form;

import org.hibernate.validator.constraints.br.CPF;

import evilasio.dev.spring_starter.domain.entity.Client;
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
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Email
    private String email;

    @CPF
    private String cpf;

    public Client generateClient() {
        return Client.builder()
                .name(this.name)
                .email(this.email)
                .cpf(this.cpf)
                .build();
    }
}
