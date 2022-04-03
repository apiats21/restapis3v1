package com.example.restapis3v1.service;

import com.example.restapis3v1.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String name);

    User findById (Long id);

    void delete(Long id);

    User save(User user);
}