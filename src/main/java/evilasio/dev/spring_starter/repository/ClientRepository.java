package evilasio.dev.spring_starter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import evilasio.dev.spring_starter.domain.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);

    Optional<Client> findByUserId(Long userId);

    Optional<Client> findByUserLogin(String userLogin);
}
