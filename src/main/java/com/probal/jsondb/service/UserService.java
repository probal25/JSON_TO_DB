package com.probal.jsondb.service;

import com.probal.jsondb.domain.User;
import com.probal.jsondb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> userList() {
        return userRepository.findAll();
    }

    public User save (User user) {
        return userRepository.save(user);
    }

    public Iterable<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }
}
