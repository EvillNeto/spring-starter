package evilasio.dev.spring_starter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import evilasio.dev.spring_starter.domain.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);

    Optional<Client> findByUserId(Long userId);

    Optional<Client> findByUserLogin(String userLogin);
}
