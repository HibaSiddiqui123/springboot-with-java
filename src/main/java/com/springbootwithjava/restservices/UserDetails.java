package com.springbootwithjava.restservices;

public class UserDetails {
    private String firstname;
    private String lastname;
    private String city;

    public UserDetails(String firstname, String lastname, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
    }
// Fields Constructor



    // Getters and Setters
    public String getLastname() {
        return lastname;
    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
