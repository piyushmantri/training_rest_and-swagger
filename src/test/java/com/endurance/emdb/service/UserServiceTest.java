package com.endurance.emdb.service;

import com.endurance.emdb.model.User;
import com.endurance.emdb.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(UserService.class)
public class UserServiceTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void createUser(){
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArgumentAt(0, User.class));

        User mockUser = new User();
        mockUser.setFirstName("Piyush");
        mockUser.setLastName("Mantri");
        mockUser.setPassword("piyush");
        mockUser.setEmailId("piyush.ma@endurance.com");
        mockUser.setUsername("piyush.ma");

        User createdUser = userService.createUser(mockUser);

        assertEquals(mockUser.getUsername(), createdUser.getUsername());
    }

    public void createUserInvalidTestOne(){
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArgumentAt(0, User.class));

        User mockUser = new User();
        mockUser.setFirstName("Piyush");
        mockUser.setLastName("Mantri");
        mockUser.setPassword("piyush");

        User createdUser = userService.createUser(mockUser);

        assertEquals(null, createdUser);
    }
}
