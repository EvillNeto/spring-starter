package evilasio.dev.spring_starter.service.register;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import evilasio.dev.spring_starter.domain.entity.User;
import evilasio.dev.spring_starter.domain.form.RegistrationForm;
import evilasio.dev.spring_starter.exception.StandardException;
import evilasio.dev.spring_starter.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerClient(RegistrationForm form) {
        if (userRepository.findByLogin(form.getLogin()).isPresent()) {
            throw new StandardException("USER_JA_EXISTE", "usuario ja existe", HttpStatus.BAD_REQUEST);
        } else {
            User user = User.builder()
                    .login(form.getLogin())
                    .roles(form.getRoles())
                    .permissions(form.getPermissions())
                    .password(passwordEncoder.encode(form.getPassword()))
                    .build();

            return userRepository.save(user);
        }
    }
}
