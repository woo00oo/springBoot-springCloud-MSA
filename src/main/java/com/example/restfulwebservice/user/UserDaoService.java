package com.example.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "hello", new Date()));
        users.add(new User(2, "hello2", new Date()));
        users.add(new User(3, "hello3", new Date()));
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


    public User findOne(Integer id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

}
