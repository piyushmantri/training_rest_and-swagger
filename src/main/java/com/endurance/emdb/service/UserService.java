package com.endurance.emdb.service;

import com.endurance.emdb.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(int id);
}
