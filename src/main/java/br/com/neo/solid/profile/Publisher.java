package br.com.neo.solid.profile;

import java.util.Arrays;
import java.util.List;

import br.com.neo.solid.permission.Permission;

public class Publisher implements Profile {

    @Override
    public List<Permission> getDefaultPermissions() {
        return Arrays.asList(
            Permission.PUBLISH,
            Permission.DELETE,
            Permission.LIST
        );
    }
    
}
