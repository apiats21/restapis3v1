package com.example.restapis3v1.dto;

import com.example.restapis3v1.model.File;
import com.example.restapis3v1.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private List<File> files;


    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setFiles(files);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setFiles(user.getFiles());

        return userDto;
    }
}