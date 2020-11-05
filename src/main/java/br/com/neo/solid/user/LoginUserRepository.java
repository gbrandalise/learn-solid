package br.com.neo.solid.user;

import java.util.Optional;

public interface LoginUserRepository extends UserRepository {

    Optional<User> findByLogin(String login);
    
}
