package com.endurance.emdb.repository;

import com.endurance.emdb.Application;
import com.endurance.emdb.Model.User;
import com.endurance.emdb.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindAllOnEmptySet(){
        List<User> users = (List<User>) userRepository.findAll();
        assertNotEquals(0, users.size());
    }

    @Test
    public void testFindUserById(){
        User user = userRepository.findOne(2);
        assertEquals("Shlok", user.getFirstName());
    }

    @Test
    public void testFindFirstByUsername(){
        User user = userRepository.findFirstByUsername("shlok1911");
        assertEquals("Shlok", user.getFirstName());
    }

    @Test
    public void testFindFirstByEmailId(){
        User user = userRepository.findFirstByEmailId("shlok.t@endurance.com");
        assertEquals("Shlok", user.getFirstName());
    }
}
