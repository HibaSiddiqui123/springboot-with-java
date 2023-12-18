package com.springbootwithjava.restservices.services;
import com.springbootwithjava.restservices.dtos.UserDtoV1;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.exceptions.ExceptionHandling;
import com.springbootwithjava.restservices.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Test
    void getAllUsers() {
        // Mocking the UserRepository behavior
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        // Calling the service method
        List<UserDtoV1> result = userService.getAllUsers();

        // Verifying the result
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }
    @Test
    void createUser() throws ExceptionHandling {
        // Mocking the UserRepository behavior
        User user = new User();
        when(userRepository.findByUsername(any())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(user);

        // Calling the service method
        User result = userService.createUser(user);

        // Verifying the result
        assertNotNull(result);

  }
    @Test
    void getUserById() throws ExceptionHandling {
        // Mocking the UserRepository behavior
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Calling the service method
        Optional<User> result = userService.getUserById(userId);

        // Verifying the result
        assertTrue(result.isPresent());
        assertEquals(user, result.get());

    }
    @Test
    void updateUserById() throws ExceptionHandling {
        // Mocking the UserRepository behavior
        Long userId = 1L;
        User existingUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any())).thenReturn(existingUser);

        // Calling the service method
        User result = userService.updateUserById(userId, new User());

        // Verifying the result
        assertNotNull(result);

    }
    @Test
    void deleteUserById() {
        // Mocking the UserRepository behavior
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        // Calling the service method
        assertDoesNotThrow(() -> userService.deleteUserById(userId));
    }


    @Test
    void getUserByUsername() {
        // Mocking the UserRepository behavior
        String username = "testUser";
        when(userRepository.findByUsername(username)).thenReturn(new User());

        // Calling the service method
        User result = userService.getUserByUsername(username);

        // Verifying the result
        assertNotNull(result);

    }




}