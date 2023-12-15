package com.springbootwithjava.restservices.controllers;

import com.springbootwithjava.restservices.repositories.UserRepository;
import com.springbootwithjava.restservices.dtos.UserMapstructDto;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @GetMapping
    public List<UserMapstructDto> getAllUserDtos(){
        return  userMapper.usersToUsersDtos(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMapstructDto getUserById(@PathVariable Long id){
        Optional<User> userOptional =userRepository.findById(id);
        User user = userOptional.get();
        return userMapper.userMapstructDto(user);
    }
}
