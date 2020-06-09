package com.yyy.blog.controller;

import com.yyy.blog.model.User;
import com.yyy.blog.service.UserService;
import com.yyy.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ApiUserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/api/user/create-user")
    public ResponseEntity<Void> createUser(@RequestBody User user){
        userService.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        Optional<User> optionalUser = userService.findUserById(id);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
