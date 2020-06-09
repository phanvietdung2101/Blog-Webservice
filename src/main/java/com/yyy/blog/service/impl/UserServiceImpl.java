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
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public boolean addNewUser(User user) {
        boolean isSave = false;
        if(user.getRole() == null){
            Role role_user = findRoleByRoleName("ROLE_USER");
            if(role_user == null){
                // Set default role
                role_user = new Role();
                role_user.setName("ROLE_USER");
            } else {
                user.setRole(role_user);
            }
        }
        try {
            this.userRepository.save(user);
            isSave = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return isSave;
    }

    @Override
    public Role findRoleByRoleName(String role_name) {
        return this.roleRepository.findRoleByName(role_name);
    }
}
