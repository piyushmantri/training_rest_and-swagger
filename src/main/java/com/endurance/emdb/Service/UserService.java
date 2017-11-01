package com.endurance.emdb.Service;

import com.endurance.emdb.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(int id);

    boolean loginUser(String username, String password);
}
