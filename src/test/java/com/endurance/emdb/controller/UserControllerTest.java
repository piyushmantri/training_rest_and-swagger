package com.endurance.emdb.controller;

import com.endurance.emdb.Controller.UserController;
import com.endurance.emdb.Model.User;
import com.endurance.emdb.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    User mockUser1 = new User();
    User mockUser2 = new User();

    List<User> allUsers = Arrays.asList(mockUser1, mockUser2);

    String mockUserJson = "{\"firstName\": \"Shlok\",\"lastName\": \"Toshniwal\",\"username\": \"shlok1911\",\"emailId\": \"shlok.t@endurance.com\",\"password\": \"shlok\"}";

    @Before
    public void init() {
        this.mockUser1.setId(1);
        this.mockUser1.setUsername("piyush0311");
        this.mockUser1.setEmailId("piyush.ma@endurance.com");
        this.mockUser1.setFirstName("Piyush");
        this.mockUser1.setLastName("Mantri");
        this.mockUser1.setPassword("password");

        this.mockUser2.setId(2);
        this.mockUser2.setUsername("shruti1810");
        this.mockUser2.setEmailId("shruti.ma@endurance.com");
        this.mockUser2.setFirstName("Shruti");
        this.mockUser2.setLastName("Mantri");
        this.mockUser2.setPassword("password");
    }

    @Test
    public void getAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(allUsers);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "[{id:1,firstName:Piyush,lastName:Mantri,username:piyush0311,emailId:piyush.ma@endurance.com,password:password,createdAt:null,updatedAt:null},{id:2,firstName:Shruti,lastName:Mantri,username:shruti1810,emailId:shruti.ma@endurance.com,password:password,createdAt:null,updatedAt:null}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getUserById() throws Exception {
        //what to do when service is called (mock the response of the service)
        Mockito.when(userService.getUserById(Mockito.anyInt())).thenReturn(mockUser1);

        //build a request (contains url to call along with what type of content controller accepts)
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1");

        //mockMvc performs the request
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //print result
        System.out.println(result.getResponse());

        //what is the expected output
        String expected = "{id:1,firstName:Piyush,lastName:Mantri,username:piyush0311,emailId:piyush.ma@endurance.com,password:password,createdAt:null,updatedAt:null}";

        //is output equal to expected output
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createUser() throws Exception {
        User sampleUser = mockUser1;

        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(sampleUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockUserJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}