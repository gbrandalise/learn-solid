package br.com.neo.solid.user;

import java.util.List;

public class UserService {

    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(Long id) {        
        return repo.findById(id).orElse(null);
    }

    public User findByLogin(String login) {        
        return repo.findByLogin(login).orElse(null);
    }

    public User persist(User user) {
        return repo.persist(user);
    }

    public boolean merge(User user) {
        return repo.merge(user);
    }
    
    public boolean delete(User user) {
        return repo.delete(user.getId());
    }
    
}
