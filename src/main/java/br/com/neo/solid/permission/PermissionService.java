package br.com.neo.solid.permission;

import java.util.Arrays;

import br.com.neo.solid.user.User;
import br.com.neo.solid.user.User.Profile;

public class PermissionService {

    public void setDefaultPermissions(User user) {
        if (user.getProfile() == Profile.ADMIN) {
            user.setPermissions(Arrays.asList(
                Permission.PUBLISH, 
                Permission.CREATE, 
                Permission.UPDATE, 
                Permission.DELETE, 
                Permission.LIST
            ));
        } else if (user.getProfile() == Profile.PUBLISHER) {
            user.setPermissions(Arrays.asList(
                Permission.PUBLISH,
                Permission.DELETE,
                Permission.LIST
            ));            
        } else {
            user.setPermissions(Arrays.asList(
                Permission.CREATE,
                Permission.LIST,
                Permission.UPDATE
            ));
        }
    }
    
}
