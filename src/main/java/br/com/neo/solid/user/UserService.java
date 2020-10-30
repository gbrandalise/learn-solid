package br.com.neo.solid.user;

import java.util.Arrays;
import java.util.List;

import br.com.neo.solid.permission.Permission;
import br.com.neo.solid.user.User.Profile;

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

    private void setDefaultPermissions(User user) {
        if (user.getProfile() == Profile.ADMIN) {
            user.setPermissions(Arrays.asList(
                Permission.PUBLISH, 
                Permission.CREATE, 
                Permission.UPDATE, 
                Permission.DELETE, 
                Permission.LIST
            ));
        } else if (user.getProfile() == Profile.PUBLISHER) {
            user.setPermissions(Arrays.asList(
                Permission.PUBLISH,
                Permission.DELETE,
                Permission.LIST
            ));            
        } else {
            user.setPermissions(Arrays.asList(
                Permission.CREATE,
                Permission.LIST,
                Permission.UPDATE
            ));
        }
    }

    public User persist(User user) {
        this.setDefaultPermissions(user);
        return repo.persist(user);
    }

    public boolean merge(User user) {
        return repo.merge(user);
    }
    
    public boolean delete(User user) {
        return repo.delete(user.getId());
    }
    
}
