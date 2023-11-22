package com.springbootwithjava.restservices.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.entities.Views;
import com.springbootwithjava.restservices.exceptions.ExceptionHandling;
import com.springbootwithjava.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value="/json/view/users")
public class UserJsonViewController {
    //Autowired the user service
    @Autowired
    private UserService userService;
    //getUserById -EXTERNAL VIEW

    @JsonView(Views.External.class)
    @GetMapping("/external/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            return  userService.getUserById(id);

        }catch(ExceptionHandling eh){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, eh.getMessage());
        }
    }

    //getUserById- INTERNAL VIEW

    @JsonView(Views.Internal.class)
    @GetMapping("/internal/{id}")
    public Optional<User> getUserById2(@PathVariable("id") @Min(1) Long id){
        try {
            return  userService.getUserById(id);

        }catch(ExceptionHandling eh){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, eh.getMessage());
        }
    }

}
