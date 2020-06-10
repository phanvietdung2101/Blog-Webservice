package com.yyy.blog.service.impl;

import com.yyy.blog.model.Role;
import com.yyy.blog.model.User;
import com.yyy.blog.repository.RoleRepository;
import com.yyy.blog.repository.UserRepository;
import com.yyy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void addNewUser(User user) {
        Optional<Role> optionalRole = roleRepository.findRoleByName("ROLE_USER");
        if(!optionalRole.isPresent()){
            Role default_role = new Role();
            default_role.setName("ROLE_USER");
            roleRepository.save(default_role);
            user.setRole(default_role);
        } else {
            user.setRole(optionalRole.get());
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<Role> findRoleByRoleName(String role_name) {
        return roleRepository.findRoleByName(role_name);
    }


}
