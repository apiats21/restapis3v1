package com.example.restapis3v1.rest;

import com.example.restapis3v1.model.User;
import com.example.restapis3v1.repository.RoleRepository;
import com.example.restapis3v1.security.jwt.JwtTokenProvider;
import com.example.restapis3v1.service.UserService;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AdminRestControllerV1.class)
class AdminRestControllerV1Test {

    @Autowired
    AdminRestControllerV1 controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    UserService userService;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    public void contextLoads()  throws Exception{
        assertThat(controller).isNotNull();

    }

    @Disabled
    @Test
    void create() throws Exception {
        User user = new User();

        Mockito.when(userService.register(user)).thenReturn(user);

        String url = "/api/v1/admin/users/create";

        mockMvc.perform(MockMvcRequestBuilders.post(url)).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        User user = new User();
        user.setId(1L);

        userService.delete(user.getId());

        String url = "/api/v1/admin/users/delete/1";


        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());

//        verify(userService, times(1), delete());
    }

    @Test
    void getAll() throws Exception {
        User user = new User();
        List<User> userList = new ArrayList<>();
        userList.add(user);

        Mockito.when(userService.getAll()).thenReturn(userList);

        String url = "/api/v1/admin/users/all";

        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        List<User> userList = new ArrayList<>();
        userList.add(user);

        Mockito.when(userService.findById(user.getId())).thenReturn(user);

        String url = "/api/v1/admin/users/1";

        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }
}