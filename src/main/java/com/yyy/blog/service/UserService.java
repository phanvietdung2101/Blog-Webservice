package com.yyy.blog.service;


import com.yyy.blog.model.Role;
import com.yyy.blog.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(long id);
    void addNewUser(User user);
    Optional<Role> findRoleByRoleName(String role_name);
}
