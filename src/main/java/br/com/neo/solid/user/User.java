package br.com.neo.solid.user;

import java.util.List;

import br.com.neo.solid.permission.Permission;
import br.com.neo.solid.profile.Profile;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    private Long id;
    @NonNull
    private String login;
    @NonNull
    private String name;
    private boolean admin;
    @NonNull
    private Profile profile;
    private List<Permission> permissions;
    
}
