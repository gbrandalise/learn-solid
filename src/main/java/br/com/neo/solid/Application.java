package br.com.neo.solid;

import java.util.List;

import br.com.neo.solid.permission.PermissionService;
import br.com.neo.solid.profile.Administrator;
import br.com.neo.solid.profile.Common;
import br.com.neo.solid.profile.Publisher;
import br.com.neo.solid.user.User;
import br.com.neo.solid.user.UserRepository;
import br.com.neo.solid.user.UserService;

public class Application {

    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        PermissionService permissionService = new PermissionService();
        UserService service = new UserService(repo, permissionService);

        User user = new User("comum", "Usuário Comum", new Administrator());
        service.persist(user);

        user = new User("admin", "Administrador", new Administrator());
        service.persist(user);

        user = new User("publicador", "Publicador", new Publisher());
        service.persist(user);

        List<User> users = service.findAll();

        System.out.println(String.format("%s usuários persistidos", users.size()));
    }
    
}
