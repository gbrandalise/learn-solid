package br.com.neo.solid.permission;

import br.com.neo.solid.user.User;

public class PermissionService {

    public void setDefaultPermissions(User user) {
        user.setPermissions(user.getProfile().getDefaultPermissions());
    }
    
}
