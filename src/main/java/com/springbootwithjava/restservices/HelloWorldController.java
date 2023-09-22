package com.springbootwithjava.restservices;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//controller
@RestController
@SpringBootApplication
public class HelloWorldController {

    //URI Method
    //GET
//    @RequestMapping(method= RequestMethod.GET, path= "/hello-world")
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World1";
    }
    @GetMapping("/hello-world-Bean")
    public  UserDetails helloWorldBean(){
        return new UserDetails("Hiba" , "Siddiqui", "karachi");

    }
}