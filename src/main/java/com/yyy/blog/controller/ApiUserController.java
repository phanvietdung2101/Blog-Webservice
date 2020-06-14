package com.yyy.blog.controller;

import com.yyy.blog.model.User;
import com.yyy.blog.service.UserService;
import com.yyy.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@EnableResourceServer
public class ApiUserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/api/user")
    public ResponseEntity<User> getUser(Principal principal){
        String username = principal.getName();
        Optional<User> optionalUser = userService.findUserByUsername(username);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
            Optional<User> optionalUser = userService.findUserById(id);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable long id){
        Optional<User> optionalUser = userService.findUserById(id);
        if(!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        userService.updateUser(user);
        return new ResponseEntity<User>(HttpStatus.OK);
    }



}
