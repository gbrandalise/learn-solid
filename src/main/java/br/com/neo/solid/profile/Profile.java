package br.com.neo.solid.profile;

import java.util.List;

import br.com.neo.solid.permission.Permission;

public interface Profile {

    List<Permission> getDefaultPermissions();
    
}
