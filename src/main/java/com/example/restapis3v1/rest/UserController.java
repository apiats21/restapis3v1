//package com.example.restapis3v1.rest;
//
//
//import com.example.restapis3v1.model.User;
//import com.example.restapis3v1.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/api/v1/admin")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping(value = "/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
//        User user = userService.findById(id);
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//}