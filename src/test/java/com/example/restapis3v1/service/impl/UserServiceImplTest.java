package com.example.restapis3v1.service.impl;

import com.example.restapis3v1.model.User;
import com.example.restapis3v1.repository.UserRepository;
import com.example.restapis3v1.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserRepository repository;

    @Test
    public void getAll() {
        User user = new User();

        when(repository.findAll()).thenReturn(Stream.of(user).collect(Collectors.toList()));

        Assert.assertEquals(1, userService.getAll().size());
    }

    @Test
    public void findByUsername() {

        User user = new User();
        user.setUsername("apiats");

        when(repository.findByUsername(user.getUsername())).thenReturn(user);

        Assert.assertEquals(user, userService.findByUsername(user.getUsername()));

    }

    @Test
    public void findById() {

        User user = new User();
        user.setId(1L);

        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        Assert.assertEquals(user, userService.findById(user.getId()));

    }

    @Test
    public void delete() {
        User user = new User();
        user.setId(1L);

        userService.delete(user.getId());

        verify(repository, times(1)).deleteById(user.getId());
    }

    @Test
    public void save() {
        User user = new User();
        user.setId(1L);

        when(repository.save(user)).thenReturn(user);

        Assert.assertEquals(user, userService.save(user));
    }
}