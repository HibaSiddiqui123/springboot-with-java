package com.springbootwithjava.restservices.controllers;

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

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    //getUserById

    @GetMapping("/{id}")
    public UserMnagementDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNameNotFoundException, ExceptionHandling {

          Optional <User> userOptional =  userService.getUserById(id);
           if (!userOptional.isPresent()){
               throw new UserNameNotFoundException("User not found");
           }
           User user =userOptional.get();
           UserMnagementDto userMnagementDto=modelMapper.map(user, UserMnagementDto.class);
           return userMnagementDto;


    }

}
