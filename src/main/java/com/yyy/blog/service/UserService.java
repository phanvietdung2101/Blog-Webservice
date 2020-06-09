package com.yyy.blog.service;


import com.yyy.blog.model.Role;
import com.yyy.blog.model.User;

public interface UserService {
    User findUserByUsername(String username);
    User findUserById(long id);
    boolean addNewUser(User user);
    Role findRoleByRoleName(String role_name);
}
