package com.example.restapis3v1.service.impl;


import com.example.restapis3v1.model.Role;
import com.example.restapis3v1.model.Status;
import com.example.restapis3v1.model.User;
import com.example.restapis3v1.repository.RoleRepository;
import com.example.restapis3v1.repository.UserRepository;
import com.example.restapis3v1.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
//        log.info("IN findByUsername - user: {} found by username: {}", result, username); //stackoverflow exception
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = (User) userRepository.findById(id).orElse(null);

        if (result == null) {
            log.info("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: found by id: {}", id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id {} was deleted successfully", id);
    }

    @Override
    public User save(User user) {
        userRepository.save(user);
        log.info("IN save - user role was saved successfully");

        return user;
    }
}