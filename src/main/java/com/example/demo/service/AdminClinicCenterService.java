package com.example.demo.service;

import com.example.demo.model.AdminClinicCenter;
import com.example.demo.model.User;
import com.example.demo.repository.AdminClinicCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminClinicCenterService {

    @Autowired
    private AdminClinicCenterRepository adminClinicCenterRepository;

//    public AdminClinicCenter findOne(Long id) {
//        return adminClinicCenterRepository.findById(id).orElseGet(null);
//    }

    public List<AdminClinicCenter> findAll() {
        return adminClinicCenterRepository.findAll();
    }

    public Page<AdminClinicCenter> findAll(Pageable page) {
        return adminClinicCenterRepository.findAll(page);
    }

    public AdminClinicCenter save(AdminClinicCenter adminClinicCenter) {
        return adminClinicCenterRepository.save(adminClinicCenter);
    }

//    public void remove(Long id) {
//        adminClinicCenterRepository.deleteById(id);
//    }

    public User findOneByEmailAndPassword(String email, String password) {
        return this.adminClinicCenterRepository.findOneByEmailAndPassword(email, password);
    }
}
