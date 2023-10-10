package com.springbootwithjava.restservices;

import com.springbootwithjava.restservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository
@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    //whenever we are writing an interface in user repository we need to ensure that what will be it's return type
    User findByUsername(String username);

}
