package evilasio.dev.spring_starter.seeder;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import evilasio.dev.spring_starter.config.properties.ConfigProperties;
import evilasio.dev.spring_starter.domain.entity.User;
import evilasio.dev.spring_starter.domain.enums.RoleEnum;
import evilasio.dev.spring_starter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultSeeder {
    
    private final ConfigProperties configProperties;
    
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public void seedAdmin() {
        if (userRepository.count() == 0) {
            log.info("CREATING ADMIN SEED");

            User admin = new User();
            admin.setLogin(configProperties.getAdmin().getLogin());
            admin.setPassword(passwordEncoder.encode(configProperties.getAdmin().getPassword()));
            admin.setRoles(Collections.singleton(RoleEnum.ADMIN));

            userRepository.save(admin);
        }
    }
}
