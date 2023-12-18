package com.springbootwithjava.restservices.controllers;
import com.springbootwithjava.restservices.dtos.UserDtoV1;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.exceptions.ExceptionHandling;
import com.springbootwithjava.restservices.exceptions.UserNameNotFoundException;
import com.springbootwithjava.restservices.services.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;


    @Test
    void testGetAllUsers() {
        // Arrange
        List<UserDtoV1> mockUsers = new ArrayList<>();
        mockUsers.add(new UserDtoV1(1L, "John Doe"));
        mockUsers.add(new UserDtoV1(2L, "Jane Doe 2"));
        mockUsers.add(new UserDtoV1(3L, "Jane Doe 3"));

        when(userService.getAllUsers()).thenReturn(mockUsers);

        // Act
        List<UserDtoV1> result = userController.getAllUsers();

        // Assert

        assertEquals(mockUsers, result);
    }


@Test
    void testCreateUser() {
        // Arrange
        User mockUser = new User();
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

        // Act
        ResponseEntity<Void> result = userController.createUser(mockUser, builder);

        // Assert


        assertNotNull(result.getHeaders().getLocation());
    }

    @Test
    void testCreateUser_WithException() throws ExceptionHandling {
        // Arrange
        User mockUser = new User();
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user")).when(userService).createUser(mockUser);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> userController.createUser(mockUser, builder));
    }

    @Test
    void testGetUserById() throws ExceptionHandling {
        // Arrange
        long userId = 1L;
        User mockUser = new User(userId);
        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        Optional<User> result = userController.getUserById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockUser, result.get());
    }

    @Test
    void testGetUserById_WithException() throws ExceptionHandling {
        // Arrange
        long userId = 1L;
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user ID")).when(userService).getUserById(userId);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> userController.getUserById(userId));
    }

    @Test
    void testUpdateUserById() throws ExceptionHandling {
        // Arrange
        long userId = 1L;
        User mockUser = new User(userId);
        when(userService.updateUserById(userId, mockUser)).thenReturn(mockUser);

        // Act
        User result = userController.UpdateUserById(userId, mockUser);

        // Assert
        assertEquals(mockUser, result);
    }

    @Test
    void testUpdateUserById_WithException() throws ExceptionHandling {
        // Arrange
        long userId = 1L;
        User mockUser = new User(userId);
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user ID")).when(userService).updateUserById(userId, mockUser);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> userController.UpdateUserById(userId, mockUser));
    }

    @Test
    void testDeleteUserById() {
        // Arrange
        long userId = 1L;

        // Act
        userController.deleteUserById(userId);

        // Assert
        Mockito.verify(userService, Mockito.times(1)).deleteUserById(userId);
    }

    @Test
    void testGetUserByUsername() throws UserNameNotFoundException {
        // Arrange
        String username = "john_doe";
        User mockUser = new User(1L);
        when(userService.getUserByUsername(username)).thenReturn(mockUser);

        // Act
        User result = userController.getUserByUsername(username);

        // Assert
        assertEquals(mockUser, result);
    }

    @Test
    void testGetUserByUsername_WithException() {
        // Arrange
        String username = "nonexistent_user";
        when(userService.getUserByUsername(username)).thenReturn(null);

        // Act & Assert
        assertThrows(UserNameNotFoundException.class, () -> userController.getUserByUsername(username));
    }
}