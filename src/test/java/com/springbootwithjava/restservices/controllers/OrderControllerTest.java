package com.springbootwithjava.restservices.controllers;

import com.springbootwithjava.restservices.entities.Order;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.exceptions.UserNameNotFoundException;
import com.springbootwithjava.restservices.repositories.OrderRepository;
import com.springbootwithjava.restservices.repositories.UserRepository;
import com.springbootwithjava.restservices.services.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private OrderRepository orderRepository;
        @Autowired
    private OrderController orderController;

    @Test
    void testGetAllOrders() throws UserNameNotFoundException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Order order = new Order();
        order.setId(1L);
        user.setOrders(Collections.singletonList(order));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertEquals(Collections.singletonList(order), orderController.getAllOrders(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetAllOrdersUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNameNotFoundException.class, () -> orderController.getAllOrders(userId));
        verify(userRepository, times(1)).findById(userId);
    }
    @Test
    void testCreateOrder() throws UserNameNotFoundException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Order order = new Order();
        order.setId(1L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(orderRepository.save(order)).thenReturn(order);

        assertEquals(order, orderController.createOrder(userId, order));
        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testCreateOrderUserNotFound() {
        Long userId = 1L;
        Order order = new Order();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNameNotFoundException.class, () -> orderController.createOrder(userId, order));
        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, never()).save(order);
    }
}