package com.springbootwithjava.restservices.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springbootwithjava.restservices.entities.User;
import com.springbootwithjava.restservices.entities.Views;
import com.springbootwithjava.restservices.exceptions.ExceptionHandling;
import com.springbootwithjava.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @RestController
    @RequestMapping(value="/jacksonfilter/users")
    @Validated
    public static class MappingJacksonController {
        @Autowired
        private UserService userService;
        //updateUserById

        // we can get filter fields through request params also to make it dynamic filtering through mapping jackson and in order to accomplish this we dont need to pass
        // fields from here we can do it directly
        @GetMapping("/{id}")
        public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
            try {

                Optional<User> userOptional = userService.getUserById(id);
                User user = userOptional.get();
                Set<String> fields = new HashSet<String>();
                // in output we are getting complete data instead of required one because id of filter in line 41 is not provided in user entity because java is unable to find symbol for "jason filter"
                fields.add("userid");
                fields.add("username");
                fields.add("ssn");
                fields.add("orders");

                FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
                MappingJacksonValue mapper = new MappingJacksonValue(user);
                mapper.setFilters(filterProvider);
                return mapper;


            } catch (ExceptionHandling eh) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, eh.getMessage());
            }

        }
    }
}
