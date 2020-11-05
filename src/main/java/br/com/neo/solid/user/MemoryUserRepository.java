package br.com.neo.solid.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryUserRepository implements LoginUserRepository {
    
    private static List<User> users = new ArrayList<>();

    @Override
    public List<User> findAll() {
        return MemoryUserRepository.users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return MemoryUserRepository.users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return MemoryUserRepository.users.stream()
            .filter(u -> u.getLogin().equals(login))
            .findFirst();
    }

    @Override
    public User persist(User user) {
        try {
            Long id = MemoryUserRepository.users.stream()
                .max((u1, u2) -> u1.getId().compareTo(u2.getId()))
                .map(User::getId)
                .orElse(0L) + 1L;
            user.setId(id);
            MemoryUserRepository.users.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public boolean merge(User user) {
        try {
            List<User> tempList = new ArrayList<>();
            MemoryUserRepository.users.stream()
                .map(u -> u.getId().equals(user.getId()) ? user : u)
                .collect(Collectors.toCollection(() -> tempList));            
            MemoryUserRepository.users.clear();
            MemoryUserRepository.users.addAll(tempList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try {
            MemoryUserRepository.users.removeIf(u -> u.getId().equals(id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
