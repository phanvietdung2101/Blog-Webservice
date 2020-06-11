package com.yyy.blog.service.impl;

import com.yyy.blog.model.Role;
import com.yyy.blog.model.User;
import com.yyy.blog.repository.RoleRepository;
import com.yyy.blog.repository.UserRepository;
import com.yyy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        Optional<User> userOptional= userRepository.findUserByUsername(username);
        if(userOptional.isPresent()){
             user =  userOptional.get();
        }
        if(user == null){
            throw new RuntimeException("user not existed");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                authorities);

        return userDetails;
    }
}
