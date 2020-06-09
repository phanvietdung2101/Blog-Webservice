package com.yyy.blog.controller;

import com.yyy.blog.model.User;
import com.yyy.blog.service.UserService;
import com.yyy.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiUserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/api/user/create-user")
    public void createUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @GetMapping("/api/user/{id}")
    public User getUser(@PathVariable long id){
        return this.userService.findUserById(id);
    }
}
