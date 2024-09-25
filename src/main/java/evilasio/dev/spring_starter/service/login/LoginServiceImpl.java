package evilasio.dev.spring_starter.service.login;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import evilasio.dev.spring_starter.domain.dto.TokenDto;
import evilasio.dev.spring_starter.domain.entity.User;
import evilasio.dev.spring_starter.domain.form.LoginForm;
import evilasio.dev.spring_starter.exception.StandardException;
import evilasio.dev.spring_starter.repository.UserRepository;
import evilasio.dev.spring_starter.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public TokenDto login(LoginForm form) {
        User user = userRepository.findByLogin(form.getLogin()).orElseThrow();

        checkPassword(user, form.getPassword());

        return new TokenDto(user, jwtService.buildToken(user.getId().toString(), user.getRoles(), user.getPermissions()));
    }


    private void checkPassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new StandardException("USUARIO_SENHA_INCORRETA","usuario ou senha incorreta",HttpStatus.BAD_REQUEST);
        }
    }
}
