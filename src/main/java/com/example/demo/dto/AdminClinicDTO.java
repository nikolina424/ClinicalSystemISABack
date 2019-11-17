package com.example.demo.dto;

import com.example.demo.model.AdminClinic;

public class AdminClinicDTO {

    private Long id;
    private String email;
    private String password;

    public AdminClinicDTO() {
    }

    public AdminClinicDTO(AdminClinic adminClinic) {
        this(adminClinic.getId(), adminClinic.getFirstName(), adminClinic.getPassword());
    }

    public AdminClinicDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
