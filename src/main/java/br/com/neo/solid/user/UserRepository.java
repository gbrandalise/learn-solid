package br.com.neo.solid.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Long id);

    User persist(User user);

    boolean merge(User user);

    boolean delete(Long id);

}
