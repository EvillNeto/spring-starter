package evilasio.dev.spring_starter.service.register;

import evilasio.dev.spring_starter.domain.entity.User;
import evilasio.dev.spring_starter.domain.form.RegistrationForm;

public interface RegisterService {
    
    public User registerClient(RegistrationForm form);
}
