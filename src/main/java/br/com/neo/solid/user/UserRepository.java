package br.com.neo.solid.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository {
    
    private static List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return UserRepository.users;
    }

    public Optional<User> findById(Long id) {
        return UserRepository.users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst();
    }

    public Optional<User> findByLogin(String login) {
        return UserRepository.users.stream()
            .filter(u -> u.getLogin().equals(login))
            .findFirst();
    }

    public User persist(User user) {
        try {
            Long id = UserRepository.users.stream()
                .max((u1, u2) -> u1.getId().compareTo(u2.getId()))
                .map(User::getId)
                .orElse(0L) + 1L;
            user.setId(id);
            UserRepository.users.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public boolean merge(User user) {
        try {
            List<User> tempList = new ArrayList<>();
            UserRepository.users.stream()
                .map(u -> u.getId().equals(user.getId()) ? user : u)
                .collect(Collectors.toCollection(() -> tempList));            
            UserRepository.users.clear();
            UserRepository.users.addAll(tempList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Long id) {
        try {
            UserRepository.users.removeIf(u -> u.getId().equals(id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
