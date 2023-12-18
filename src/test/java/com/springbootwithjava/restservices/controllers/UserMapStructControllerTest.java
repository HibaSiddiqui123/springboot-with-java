package com.springbootwithjava.restservices.controllers;
import com.springbootwithjava.restservices.dtos.UserMapstructDto;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.mappers.UserMapper;
import com.springbootwithjava.restservices.repositories.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@WebMvcTest(UserMapStructController.class)
class UserMapStructControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserMapStructController userMapStructController;

  @Test
    void testGetAllUserDtos() {
        // Mocking repository response
        List<User> users = Arrays.asList(new User(1L, "John"), new User(2L, "Jane"));
        when(userRepository.findAll()).thenReturn(users);

        // Mocking mapper response
        List<UserMapstructDto> userDtos = Arrays.asList(new UserMapstructDto(1L, "John"), new UserMapstructDto(2L, "Jane"));
        when(userMapper.usersToUsersDtos(users)).thenReturn(userDtos);

        // Performing the test
        List<UserMapstructDto> result = userMapStructController.getAllUserDtos();

        // Verifying the results
        assertEquals(userDtos, result);

    }
    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User(userId, "John");

        // Mocking repository response
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Mocking mapper response
        UserMapstructDto userDto = new UserMapstructDto(userId, "John");
        when(userMapper.userMapstructDto(user)).thenReturn(userDto);

        // Performing the test
        UserMapstructDto result = userMapStructController.getUserById(userId);

        // Verifying the results
        assertEquals(userDto, result);
    }

}