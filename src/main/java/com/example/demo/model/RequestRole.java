package com.example.demo.model;

import lombok.Getter;

@Getter
public enum RequestRole {

    REGISTRATION("REGISTRATION"), HOLIDAY("HOLIDAY"), EXAMINATION("EXAMINATION"), OPERATION("OPERATION");

    private String role;

    RequestRole(String role) {
        this.role = role;
    }
}
