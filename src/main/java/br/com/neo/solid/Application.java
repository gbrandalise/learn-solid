package br.com.neo.solid;

import java.util.List;

import br.com.neo.solid.user.User;
import br.com.neo.solid.user.UserRepository;
import br.com.neo.solid.user.UserService;
import br.com.neo.solid.user.User.UserType;

public class Application {

    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        UserService service = new UserService(repo);

        User user = new User("comum", "Usuário Comum", UserType.COMMON);
        service.persist(user);

        user = new User("admin", "Administrador", UserType.ADMIN);
        service.persist(user);

        user = new User("publicador", "Publicador", UserType.PUBLISHER);
        service.persist(user);

        List<User> users = service.findAll();

        System.out.println(String.format("%s usuários persistidos", users.size()));
    }
    
}
