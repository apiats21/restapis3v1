package com.example.restapis3v1.rest;

import com.example.restapis3v1.dto.UserDto;
import com.example.restapis3v1.model.Role;
import com.example.restapis3v1.model.User;
import com.example.restapis3v1.repository.RoleRepository;
import com.example.restapis3v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;
    private final RoleRepository roleRepository;


    @Autowired
    public AdminRestControllerV1(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "users/create")
    public ResponseEntity<UserDto> create(@RequestBody User user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());


        userService.register(user);
        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "users/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);

        return "User deleted";

    }

    @GetMapping(value = "users/all")
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users = userService.getAll();
        List<UserDto> result = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = UserDto.fromUser(user);
            result.add(userDto);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "users/roles/{id}/{roleId}")
    public void setRole(@PathVariable(name = "id") Long id, @PathVariable(name = "roleId") Long roleId) {
        User user = userService.findById(id);

        Role roleUser = roleRepository.findById(roleId).orElse(null);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setRoles(userRoles);

        userService.save(user);
    }
}
