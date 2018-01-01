package com.endurance.emdb.controller;

import com.endurance.emdb.model.User;
import com.endurance.emdb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
@Api(value="User controller")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get All Users")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        return (user == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return (createdUser == null) ? new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE) : new ResponseEntity<User>(createdUser, HttpStatus.CREATED) ;
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
