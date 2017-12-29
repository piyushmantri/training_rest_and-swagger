package com.endurance.emdb.Controller;

import com.endurance.emdb.Model.User;
import com.endurance.emdb.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@Controller
@RequestMapping("user")
@Api(value="User Controller", description="User related all operations")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get All Users")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        return (user == null) ? new ResponseEntity<User>(HttpStatus.NOT_FOUND) : new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return (createdUser == null) ? new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE) : new ResponseEntity<User>(createdUser, HttpStatus.CREATED) ;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> loginUser(@RequestPart("username") String username, @RequestPart("password") String password){
        boolean is_login_success = userService.loginUser(username, password);
        return new ResponseEntity<Boolean>(is_login_success, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
