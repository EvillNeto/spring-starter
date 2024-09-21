package evilasio.dev.spring_starter.domain.entity;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends BaseEntity {

    @Column(nullable = false)
    String name;

    @Email
	@Column(nullable = false)
    String email;
    
    @CPF
	@Column(nullable = false)
    String cpf;
}
