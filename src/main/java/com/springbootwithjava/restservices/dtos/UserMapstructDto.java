package com.springbootwithjava.restservices.dtos;

public class UserMapstructDto {
    private  Long userid;
    private String username;
    private String email;

    public UserMapstructDto() {
    }

    public UserMapstructDto(Long userid, String username, String email) {
        this.userid = userid;
        this.username = username;
        this.email = email;
    }

    public UserMapstructDto(long l, String john) {
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
