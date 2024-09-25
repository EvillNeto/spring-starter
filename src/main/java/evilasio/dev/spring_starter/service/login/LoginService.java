package evilasio.dev.spring_starter.service.login;

import evilasio.dev.spring_starter.domain.dto.TokenDto;
import evilasio.dev.spring_starter.domain.form.LoginForm;

public interface LoginService {
    
    public TokenDto login(LoginForm form);
}
