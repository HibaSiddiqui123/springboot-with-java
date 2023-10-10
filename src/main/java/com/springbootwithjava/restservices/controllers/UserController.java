package com.springbootwithjava.restservices.controllers;


//Controller

import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    //Autowired the user service
    @Autowired
    private UserService userService;

    //getAllUsers Method
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }
    //create user method
    //@RequestBody Annotation
    //@PostMapping Annotation
    @PostMapping("/users")
    public  User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    //getUserById
    //path variable id will help to get user by id
    //way 1: we can write method and then annotate it with get mapping
    //way 2: otherwise we can use same value of id in annotation and in method name
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id")Long id){
        return  userService.getUserById(id);
    }
    //updateUserById
    @PutMapping("/users/{id}")
    public  User UpdateUserById(@PathVariable("id")Long id, @RequestBody User user){
        //we will update user and then send it to the rest client
        return userService.updateUserById(id, user);
    }

    //delete users
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
         userService.deleteUserById(id);
    }

    //getUserByUsername method
    @GetMapping("/users/byusername/{username}")


    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);

    }

}
