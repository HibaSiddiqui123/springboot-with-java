package com.springbootwithjava.restservices.controllers;

import com.springbootwithjava.restservices.dtos.UserDtoV1;
import com.springbootwithjava.restservices.dtos.UserDtoV2;
import com.springbootwithjava.restservices.dtos.UserMnagementDto;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.exceptions.ExceptionHandling;
import com.springbootwithjava.restservices.exceptions.UserNameNotFoundException;
import com.springbootwithjava.restservices.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersioningController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    //getUserById

    //  URI based versioning - V1
    @GetMapping({"/V1.0/{id}", "/V1.1/{id}"} )
    public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNameNotFoundException, ExceptionHandling {

        Optional <User> userOptional =  userService.getUserById(id);
        if (!userOptional.isPresent()){
            throw new UserNameNotFoundException("User not found");
        }
        User user =userOptional.get();
        UserDtoV1 userDtoV1=modelMapper.map(user, UserDtoV1.class);
        return userDtoV1;


    }

    //  URI based versioning - V2
    @GetMapping("/V2.0/{id}" )
    public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNameNotFoundException, ExceptionHandling {

        Optional <User> userOptional =  userService.getUserById(id);
        if (!userOptional.isPresent()){
            throw new UserNameNotFoundException("User not found");
        }
        User user =userOptional.get();
        UserDtoV2 userDtoV2=modelMapper.map(user, UserDtoV2.class);
        return userDtoV2;


    }

}
