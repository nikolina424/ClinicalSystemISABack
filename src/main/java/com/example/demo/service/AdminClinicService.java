package com.example.demo.service;

import com.example.demo.model.AdminClinic;
import com.example.demo.model.User;
import com.example.demo.repository.AdminClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminClinicService {
    @Autowired
    private AdminClinicRepository adminClinicRepository;

//    public AdminClinic findOne(Long id) {
//        return adminClinicRepository.findById(id).orElseGet(null);
//    }

    public List<AdminClinic> findAll() {
        return adminClinicRepository.findAll();
    }

    public Page<AdminClinic> findAll(Pageable page) {
        return adminClinicRepository.findAll(page);
    }

    public AdminClinic save(AdminClinic adminClinic) {
        return adminClinicRepository.save(adminClinic);
    }

//    public void remove(Long id) {
//        adminClinicRepository.deleteById(id);
//    }

    public User findOneByEmailAndPassword(String email, String password) {
        return this.adminClinicRepository.findOneByEmailAndPassword(email, password);
    }
}
