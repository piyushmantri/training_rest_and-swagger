package com.endurance.emdb.controller;

import com.endurance.emdb.Controller.UserController;
import com.endurance.emdb.Model.User;
import com.endurance.emdb.Service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserController userController;

    @MockBean
    UserService userService;

    @BeforeEach
    public void init(){
        //truncate all tables

    }

    @Test
    public void getAllUsers(){
        Mockito.when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        ResponseEntity<List<User>> allUsers = userController.getAllUsers();

        assertThat(allUsers.equals(Collections.emptyList()));

        assertEquals(HttpStatus.OK, allUsers.getStatusCode());
    }

    @Test
    public void getUserByIdOnInvalidUserIdTest(){
        ResponseEntity<User> allUsers = userController.getUserById(0);

        assertEquals(HttpStatus.NOT_FOUND, allUsers.getStatusCode());
    }

    @Test
    public void createValidUser(){
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenAnswer(i -> i.getArgumentAt(0, User.class));

        User mockUser = new User();
        mockUser.setFirstName("Piyush");
        mockUser.setLastName("Mantri");
        mockUser.setPassword("piyush");
        mockUser.setEmailId("piyush.ma@endurance.com");
        mockUser.setUsername("piyush.ma");

        ResponseEntity<User> user = userController.createUser(mockUser);

        assertEquals(HttpStatus.CREATED, user.getStatusCode());
        assertEquals(mockUser.getUsername(), user.getBody().getUsername());
    }

    @Test
    public void createUserWithLessData(){
        User mockUser = new User();
        mockUser.setFirstName("Piyush");
        mockUser.setLastName("Mantri");
        mockUser.setUsername("piyush.ma");

        ResponseEntity<User> user = userController.createUser(mockUser);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, user.getStatusCode());
        assertEquals(null, user.getBody());
    }
}
