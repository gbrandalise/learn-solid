package br.com.neo.solid.user;

import java.util.Arrays;
import java.util.List;

import br.com.neo.solid.profile.Profile;
import br.com.neo.solid.user.User.UserType;

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
        if (user.getUserType() == UserType.ADMIN) {
            user.setProfiles(Arrays.asList(
                Profile.ADMIN
            ));
        } else if (user.getUserType() == UserType.PUBLISHER) {
            user.setProfiles(Arrays.asList(
                Profile.PUBLISH,
                Profile.DELETE,
                Profile.LIST
            ));            
        } else if (user.getUserType() == UserType.COMMON) {
            user.setProfiles(Arrays.asList(
                Profile.CREATE,
                Profile.LIST,
                Profile.UPDATE
            ));
        }
        return repo.persist(user);
    }

    public boolean merge(User user) {
        return repo.merge(user);
    }
    
    public boolean delete(User user) {
        return repo.delete(user.getId());
    }
    
}
