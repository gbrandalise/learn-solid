package br.com.neo.solid.user;

import java.util.List;

import br.com.neo.solid.permission.PermissionService;

public class UserService {

    private UserRepository repo;
    private PermissionService permissionService;

    public UserService(UserRepository repo, PermissionService permissionService) {
        this.repo = repo;
        this.permissionService = permissionService;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(Long id) {        
        return repo.findById(id).orElse(null);
    }

    public User persist(User user) {
        permissionService.setDefaultPermissions(user);
        return repo.persist(user);
    }

    public boolean merge(User user) {
        return repo.merge(user);
    }
    
    public boolean delete(User user) {
        return repo.delete(user.getId());
    }
    
}
