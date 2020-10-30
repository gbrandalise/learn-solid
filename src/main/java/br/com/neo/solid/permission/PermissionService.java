package br.com.neo.solid.permission;

import br.com.neo.solid.profile.Profile;
import br.com.neo.solid.user.User;

public class PermissionService {

    public void setDefaultPermissions(User user) {
        Profile profile = user.getProfile();
        user.setPermissions(profile.getDefaultPermissions());
    }
    
}
