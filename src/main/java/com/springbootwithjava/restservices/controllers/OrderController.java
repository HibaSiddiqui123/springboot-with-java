package com.springbootwithjava.restservices.controllers;


import com.springbootwithjava.restservices.repositories.OrderRepository;
import com.springbootwithjava.restservices.repositories.UserRepository;
import com.springbootwithjava.restservices.entities.Order;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.exceptions.UserNameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    //get all orders from user

    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/{userid}/orders")

    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNameNotFoundException{
        Optional<User> userOptional = userRepository.findById(userid);
        if(!userOptional.isPresent())
            throw new UserNameNotFoundException("User not found");
        return userOptional.get().getOrders();

    }

    //Create order method
    @PostMapping("/{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNameNotFoundException{
        Optional<User> userOptional = userRepository.findById(userid);
        if(!userOptional.isPresent())
            throw new UserNameNotFoundException("User not found");
        User user =userOptional.get();
        order.setUser(user);
      return  orderRepository.save(order);


    }

}
