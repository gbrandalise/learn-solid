package br.com.neo.solid.user;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import br.com.neo.solid.profile.Common;

public class FileUserRepository implements UserRepository {

    private Path dir = Paths.get("c:\\user_repository");
    private static Long lastId = 0L;
    private static final String ID_ATTRIBUTE = "id=";
    private static final String LOGIN_ATTRIBUTE = "login=";
    private static final String NAME_ATTRIBUTE = "name=";

    public FileUserRepository() {
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Long getNextId() {
        FileUserRepository.lastId++;
        return FileUserRepository.lastId;
    }

    private User readUserByFile(Path file) {
        try (Stream<String> lines = Files.lines(file)) {
            User user = new User("", "", new Common());
            lines.forEach(l -> {
                if (l.contains(ID_ATTRIBUTE)) {
                    user.setId(Long.valueOf(l.substring(ID_ATTRIBUTE.length())));
                } else if (l.contains(LOGIN_ATTRIBUTE)) {
                    user.setLogin(l.substring(LOGIN_ATTRIBUTE.length()));
                } else if (l.contains(NAME_ATTRIBUTE)) {
                    user.setName(l.substring(NAME_ATTRIBUTE.length()));
                }
            });
            return user;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeUserToFile(User user, Path file) {
        try (
            BufferedWriter bw = Files.newBufferedWriter(
                file, 
                Charset.defaultCharset()
            )
        ) {
            bw.write("");
            bw.write(ID_ATTRIBUTE + user.getId());
            bw.newLine();
            bw.write(LOGIN_ATTRIBUTE + user.getLogin());
            bw.newLine();
            bw.write(NAME_ATTRIBUTE+ user.getName());
            bw.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Stream<Path> files = Files.list(dir)) {
            files.forEach(file -> users.add(readUserByFile(file)));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Stream<Path> files = Files.list(dir)) {
            Path file = files
                .filter(f -> f.getFileName().startsWith("user" + id))
                .findAny()
                .orElse(null);
            return Optional.of(readUserByFile(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public User persist(User user) {
        user.setId(getNextId());
        try {
            writeUserToFile(user, Files.createFile(
                Paths.get(
                    dir.toString() + File.separator + "user" + user.getId() + ".txt"
                )
            ));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean merge(User user) {
        try (Stream<Path> files = Files.list(dir)) {
            Path file = files
                .filter(f -> f.getFileName().endsWith("user" + user.getId()))
                .findAny()
                .orElse(null);
            writeUserToFile(user, file);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            Files.delete(Paths.get(dir.toString() + File.separator + "user" + id));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
