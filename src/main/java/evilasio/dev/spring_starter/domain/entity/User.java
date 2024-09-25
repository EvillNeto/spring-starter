package evilasio.dev.spring_starter.domain.entity;

import java.util.Set;

import evilasio.dev.spring_starter.domain.enums.PermissionEnum;
import evilasio.dev.spring_starter.domain.enums.RoleEnum;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false, unique=true)
    private String login;

    @Column(nullable = false)
    private String password;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles")
	@Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

    @ElementCollection(targetClass = PermissionEnum.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_permissions")
	@Enumerated(EnumType.STRING)
    private Set<PermissionEnum> permissions;

    @OneToOne(mappedBy = "user")
    private Client client;
}
