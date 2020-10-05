package br.com.neo.solid.user;

import java.util.List;

import br.com.neo.solid.profile.Profile;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class User {

    public enum UserType {
        ADMIN, COMMON, PUBLISHER
    }

    private Long id;
    @NonNull
    private String login;
    @NonNull
    private String name;
    @NonNull
    private UserType userType;
    private List<Profile> profiles;
    
}
