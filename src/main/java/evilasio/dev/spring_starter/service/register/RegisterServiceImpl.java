package evilasio.dev.spring_starter.service.register;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import evilasio.dev.spring_starter.domain.entity.Client;
import evilasio.dev.spring_starter.domain.entity.User;
import evilasio.dev.spring_starter.domain.enums.RoleEnum;
import evilasio.dev.spring_starter.domain.form.ClientRegistrationForm;
import evilasio.dev.spring_starter.exception.StandardException;
import evilasio.dev.spring_starter.repository.ClientRepository;
import evilasio.dev.spring_starter.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Client registerClient(ClientRegistrationForm form) {
        if (clientRepository.findByUserLogin(form.getLogin()).isPresent()) {
            throw new StandardException("CLIENT_JA_EXISTE", "cliente ja existe", HttpStatus.BAD_REQUEST);
        }

        Client novoClient = form.generateClient();
        novoClient.setUser(getUser(form));

        return clientRepository.save(novoClient);
    }

    private User getUser(ClientRegistrationForm form) {
        Optional<User> optUser = userRepository.findByLogin(form.getLogin());
        if (optUser.isPresent()) {
            User user = optUser.get();

            Set<RoleEnum> roles = user.getRoles();
            roles.add(RoleEnum.CLIENT);
            user.setRoles(roles);

            return userRepository.save(user);
        } else {
            User user = User.builder()
                    .login(form.getLogin())
                    .roles(Collections.singleton(RoleEnum.CLIENT))
                    .password(passwordEncoder.encode(form.getPassword()))
                    .build();

            return userRepository.save(user);
        }
    }

}
