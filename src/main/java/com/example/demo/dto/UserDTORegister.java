package com.example.demo.dto;

import com.example.demo.model.User;

public class UserDTORegister {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private Integer phoneNumber;
    private Integer userId;
    private String email;
    private String password;
    private String role;
    private String token;

    public UserDTORegister() {
    }

    public UserDTORegister(Long id, String firstName, String lastName, String address, String city, String country, Integer phoneNumber, Integer userId, String email, String password, String role, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    public UserDTORegister(User newUser) {
        this(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getAddress(), newUser.getCity(), newUser.getCountry(), newUser.getPhoneNumber(), newUser.getUserId(), newUser.getEmail(), newUser.getPassword(), newUser.getRoleValue(), "");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
