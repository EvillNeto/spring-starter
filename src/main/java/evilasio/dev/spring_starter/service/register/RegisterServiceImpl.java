package evilasio.dev.spring_starter.service.register;

import org.springframework.stereotype.Service;

import evilasio.dev.spring_starter.domain.entity.Client;
import evilasio.dev.spring_starter.domain.form.ClientRegistrationForm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    @Override
    public Client registerClient(ClientRegistrationForm form) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerClient'");
    }
    
}
