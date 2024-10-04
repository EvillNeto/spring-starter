package evilasio.dev.spring_starter.service.permission;

import evilasio.dev.spring_starter.domain.entity.User;
import evilasio.dev.spring_starter.domain.form.PermissionForm;

public interface PermissionService {

    public User changePermissions(Long id, PermissionForm form);
}
