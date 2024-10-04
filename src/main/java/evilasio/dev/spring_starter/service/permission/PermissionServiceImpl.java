package evilasio.dev.spring_starter.service.permission;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import evilasio.dev.spring_starter.domain.entity.User;
import evilasio.dev.spring_starter.domain.form.PermissionForm;
import evilasio.dev.spring_starter.exception.StandardException;
import evilasio.dev.spring_starter.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final UserRepository userRepository;

    @Override
    public User changePermissions(Long id, PermissionForm form) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new StandardException("USER_NOT_FOUND", "usuario não encontrado",
                        HttpStatus.BAD_REQUEST));
                        
        user.setRoles(form.getRoles());
        user.setPermissions(form.getPermissions());

        return userRepository.save(user);
    }

}
