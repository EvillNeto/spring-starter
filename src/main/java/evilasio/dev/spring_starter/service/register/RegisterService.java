package evilasio.dev.spring_starter.service.register;

import evilasio.dev.spring_starter.domain.entity.Client;
import evilasio.dev.spring_starter.domain.form.ClientRegistrationForm;

public interface RegisterService {
    
    public Client registerClient(ClientRegistrationForm form);
}
