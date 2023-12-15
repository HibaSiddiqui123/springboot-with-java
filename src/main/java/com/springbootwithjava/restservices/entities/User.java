package com.springbootwithjava.restservices.entities;

//Entity == represents table store in a
//Every instance of entity represents row in a DB

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
//schema distinguish one set of table with another
//like we can define schema @Table(name="user", schema="user-management")
//we are not using schema here because H2 DB is already using tested DB as it's schema name here


@Entity
@Table(name="users")
public class User {
//For telling JPA that it is our primary keys we need to annotate it with ID

    @Id
    @GeneratedValue //For auto-generating values for id

// ID is a primary key here
    @JsonView(Views.External.class)
    private Long id;
// we can also define length for the field or if  it can be null or not or should it be unique in a table
//In relational DB we should define only one field as unique key but we can define more
    @NotBlank(message = "Name may not be null")
//    @Column(name= "username", length = 50, nullable = false, unique = true)
    @JsonView(Views.External.class)
    private String username;
    @Size(min=2, message = "Firstname should have atleast two characters ")
    @Column(name= "FIRST_NAME", length = 50, nullable = false)
    @JsonView(Views.External.class)

    private String firstname;
    @Column(name= "LAST_NAME", length = 50, nullable = false)
    @JsonView(Views.External.class)

    private String lastname;
    @Column(name= "EMAIL_ADDRESS", length = 50, nullable = false)
    @JsonView(Views.External.class)

    private String email;
    @Column(name= "ROLE", length = 50, nullable = false)
    @JsonView(Views.Internal.class)

    private String role;

    @Column(name= "SSN", length = 50, nullable = false, unique = true)
    @JsonView(Views.Internal.class)

    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonView(Views.Internal.class)

    private List<Order> orders;
    @Column(name = "ADDRESS")
    private String address;


    // Fields Constructor
    public User(Long id) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
        this.address = address;

    }

    //No argument constructor
   public User(){

}

    public User(long l, String johnDoe) {
        this(l);
        this.username = johnDoe;
    }
    public User(long l, String johnDoe, List<Order> orders) {
        this(l,johnDoe);
        this.orders=orders;
    }
// GETTER and SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //To String (optional required for bean logging)


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                ", orders=" + orders +
                ", address='" + address + '\'' +
                '}';
    }
}

// In memory DB (Data will be lost when we restart JVM or when JVM reloads so we will repopulate DB on runtime)