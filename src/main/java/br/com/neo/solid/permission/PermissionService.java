package br.com.neo.solid.profile;

import br.com.neo.solid.profile.Profile;
import br.com.neo.solid.user.User;
import br.com.neo.solid.user.User.Profile;

public class PermissionService {

    public void setDefaultPermissions(User user) {
        Profile profile = user.getProfile();
        user.setPermissions(profile.getDefaultPermissions());
    }
    
}
