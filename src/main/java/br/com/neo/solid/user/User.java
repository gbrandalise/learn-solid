package br.com.neo.solid.user;

import java.util.List;

import br.com.neo.solid.profile.Permission;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    public enum Profile {
        ADMIN, COMMON, PUBLISHER
    }

    private Long id;
    @NonNull
    private String login;
    @NonNull
    private String name;
    @NonNull
    private Profile userType;
    private List<Permission> profiles;
    
}
