package evilasio.dev.spring_starter.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Principal {
    
    private String subject;

    private List<String> roles;

    private List<String> permissions;

    private String token;
}
