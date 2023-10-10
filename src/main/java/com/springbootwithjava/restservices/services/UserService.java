package com.springbootwithjava.restservices.services;


//Service

import com.springbootwithjava.restservices.UserRepository;
import com.springbootwithjava.restservices.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    //Autowire the User Repository
    @Autowired
    private UserRepository userRepository;

    //getAll users Method
    //return type is list and users will be returning
//this method will call getAllUsers method from User Controller and then bring data from the database
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    // CreateUser Method
//Request body is User user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //getUserById
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    //updateUserById method
    //In this method we are getting user and Id and then set  the id in persistant context
    //then the same user will be updated by the user body
    public User updateUserById(Long id, User user) {
        var userEntity = userRepository.findById(id).orElseThrow();
        if (Objects.nonNull(userEntity)) {
            userEntity.setFirstname(user.getFirstname());
        }
        //now we need to save this user then return it
        return userRepository.save(userEntity);

    }
    //deleteUserById method

    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }


    }

    //getUserByUsername
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}