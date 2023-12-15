package com.springbootwithjava.restservices.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import com.springbootwithjava.restservices.entities.Order;
import com.springbootwithjava.restservices.entities.Views;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class UserDtoV1 {


    private Long id;

    private String username;

    private String firstname;

    private String lastname;


    private String email;

    private String role;


    private String ssn;


    private List<Order> orders;

    public UserDtoV1() {
    }

    public UserDtoV1(Long id, String username, String firstname, String lastname, String email, String role, String ssn, List<Order> orders) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
        this.orders = orders;
    }

    public UserDtoV1(long l, String johnDoe) {
        this.id=l;
        this.username=johnDoe;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDtoV1 userDtoV1 = (UserDtoV1) o;
        return Objects.equals(id, userDtoV1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
