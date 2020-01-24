package com.codecool.serviceuser.controller;

import com.codecool.serviceuser.entity.User;
import com.codecool.serviceuser.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    @ApiOperation("Get all users")
    public List<User> listAllUsers() {
        log.info("== GET request received to /user/ endpoint");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a user by id")
    public User getUser(@PathVariable Long id) {
        log.info("== GET request received to /user/" + id + " endpoint");
        return userService.getUserById(id);
    }

    @GetMapping("/byname/{username}")
    public User getUserBy(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/registration")
    @ApiOperation("Update or create a user")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

}
