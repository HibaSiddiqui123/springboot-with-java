package com.springbootwithjava.restservices.services;


//Service

import com.springbootwithjava.restservices.dtos.UserDtoV1;
import com.springbootwithjava.restservices.exceptions.ExceptionHandling;
import com.springbootwithjava.restservices.repositories.UserRepository;
import com.springbootwithjava.restservices.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    //Autowire the User Repository
    @Autowired
    private UserRepository userRepository;

    //getAll users Method
    //return type is list and users will be returning
//this method will call getAllUsers method from User Controller and then bring data from the database
    public List<UserDtoV1> getAllUsers() {
        return userRepository.findAll().stream().map(m -> new UserDtoV1(m.getId(),m.getUsername(),m.getFirstname(),m.getLastname(),m.getEmail(),m.getRole(),m.getSsn(),m.getOrders())).collect(Collectors.toList());

    }

    // CreateUser Method
//Request body is User user
    public User createUser(User user) throws ExceptionHandling {
        User existingUser=userRepository.findByUsername(user.getUsername());
        if(existingUser!=null){
            throw new ExceptionHandling("User already exists");
        }
        return userRepository.save(user);
    }

    //getUserById
    public Optional<User> getUserById(Long id) throws ExceptionHandling {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new ExceptionHandling("User Not Found In Data");
        }
        return user;
    }

    //updateUserById method
    //In this method we are getting user and Id and then set  the id in persistant context
    //then the same user will be updated by the user body
    public User updateUserById(Long id, User user)  throws ExceptionHandling{

        Optional<User> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()){
            throw new ExceptionHandling(("User Not Found In Data, kindly provide correct id"));

        }
        //now we need to save this user then return it
        return userRepository.save(user);

    }
    //deleteUserById method

    public void deleteUserById(Long id) {

            Optional<User> userEntity = userRepository.findById(id);
            if(!userEntity.isPresent()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in the data,kindly provide correct id ");

            }



            userRepository.deleteById(id);



    }

    //getUserByUsername
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}