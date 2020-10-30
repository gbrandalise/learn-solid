package br.com.neo.solid.profile;

import java.util.Arrays;
import java.util.List;

import br.com.neo.solid.permission.Permission;

public class Administrator implements Profile {

    @Override
    public List<Permission> getDefaultPermissions() {
        return Arrays.asList(
            Permission.PUBLISH, 
            Permission.CREATE, 
            Permission.UPDATE, 
            Permission.DELETE, 
            Permission.LIST
        );
    }
    
}
