package evilasio.dev.spring_starter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import evilasio.dev.spring_starter.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

}
