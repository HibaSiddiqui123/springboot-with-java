package com.springbootwithjava.restservices.controllers;


//Controller

import com.springbootwithjava.restservices.dtos.UserDtoV1;
import com.springbootwithjava.restservices.exceptions.ExceptionHandling;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.exceptions.UserNameNotFoundException;
import com.springbootwithjava.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Optional;



@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {
    //Autowired the user service
    @Autowired
    private UserService userService;

    //getAllUsers Method
    @GetMapping
    public List<UserDtoV1> getAllUsers(){
        return userService.getAllUsers();

    }
    //create user method
    //@RequestBody Annotation
    //@PostMapping Annotation

    @PostMapping

    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){
        try{
             userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            //setting location header
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            //returning response entity
            return new ResponseEntity(headers, HttpStatus.CREATED);


        }catch(ExceptionHandling eh){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, eh.getMessage());
        }

    }


    //getUserById
    //path variable id will help to get user by id
    //way 1: we can write method and then annotate it with get mapping
    //way 2: otherwise we can use same value of id in annotation and in method name
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            return  userService.getUserById(id);

        }catch(ExceptionHandling eh){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, eh.getMessage());
        }
    }
    //updateUserById
    @PutMapping("/{id}")
    public  User UpdateUserById(@PathVariable("id")Long id, @RequestBody User user){
        try{
            return  userService.updateUserById(id, user);
        }catch(ExceptionHandling eh){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, eh.getMessage());
        }
    }

    //delete users
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
         userService.deleteUserById(id);
    }

    //getUserByUsername method
    @GetMapping("/byusername/{username}")


    public User getUserByUsername(@PathVariable("username") String username)throws UserNameNotFoundException{

        User user= userService.getUserByUsername(username);
        if(user == null)
            throw  new UserNameNotFoundException("Username : '"+username+"' not found in User repository");
        return user;




















    }

}
