package evilasio.dev.spring_starter.domain.dto;

import evilasio.dev.spring_starter.domain.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;

    private String name;

    private String email;

    private String cpf;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.cpf = client.getCpf();
    }

    public static ClientDto toDto(Client client) {
        return client == null ? null : new ClientDto(client);
    }
}
