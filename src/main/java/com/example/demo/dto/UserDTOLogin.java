package com.example.demo.dto;

import com.example.demo.model.User;

public class UserDTOLogin {

    private Long id;
    private String email;
    private String password;
    private String role;
    private String token;

    public UserDTOLogin() {
    }

    public UserDTOLogin(User user) {
        this(user.getId(), user.getEmail(), user.getPassword(), user.getRoleValue(), "");
    }

    public UserDTOLogin(Long id, String email, String password, String role, String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }
}
