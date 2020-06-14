package com.yyy.blog.controller;

import com.yyy.blog.model.User;
import com.yyy.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/api/user/register")
    public ResponseEntity<Void> createUser(@RequestBody User user){
        userService.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
