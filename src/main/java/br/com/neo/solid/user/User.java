package br.com.neo.solid.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    public enum UserType {
        ADMIN, COMMON, PUBLISHER
    }

    private Long id;
    private String login;
    private String name;
    private UserType userType;
    
}
