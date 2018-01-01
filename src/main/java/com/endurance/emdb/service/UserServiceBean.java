package com.endurance.emdb.service;

import com.endurance.emdb.model.User;
import com.endurance.emdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceBean implements UserService{
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User createUser(User user) {
        if (user.getId() != 0 ||
                user.getPassword() == null ||
                user.getEmailId() == null ||
                user.getUsername() == null ){
            return null;
        }
        return userRepository.save(user);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User updateUser(User user) {
        User userToUpdate = userRepository.findOne(user.getId());
        if (userToUpdate == null){
            return null;
        }
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmailId(user.getEmailId());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}
