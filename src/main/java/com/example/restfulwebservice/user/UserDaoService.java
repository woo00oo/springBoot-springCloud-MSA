package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "hello", new Date(), "test1", "123123123"));
        users.add(new User(2, "hello2", new Date(), "test2", "123123123"));
        users.add(new User(3, "hello3", new Date(), "test3", "123123123"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }

        users.add(user);
        return user;
    }

    public Optional<User> findOne(Integer id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findAny();
    }

    public User deleteById(Integer id) {
        Optional<User> optionalUser = findOne(id);

        return optionalUser
                .map(user -> {
                    users.remove(user);
                    return user;
                })
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", id)));
    }

}
