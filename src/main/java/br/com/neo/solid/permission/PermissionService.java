package br.com.neo.solid.profile;

import java.util.Arrays;

import br.com.neo.solid.user.User;
import br.com.neo.solid.user.User.Profile;

public class PermissionService {

    public void setDefaultPermissions(User user) {
        if (user.getUserType() == Profile.ADMIN) {
            user.setProfiles(Arrays.asList(
                Permission.PUBLISH, 
                Permission.CREATE, 
                Permission.UPDATE, 
                Permission.DELETE, 
                Permission.LIST
            ));
        } else if (user.getUserType() == Profile.PUBLISHER) {
            user.setProfiles(Arrays.asList(
                Permission.PUBLISH,
                Permission.DELETE,
                Permission.LIST
            ));            
        } else {
            user.setProfiles(Arrays.asList(
                Permission.CREATE,
                Permission.LIST,
                Permission.UPDATE
            ));
        }
    }
    
}
