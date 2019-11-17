package com.example.demo.repository;

import com.example.demo.model.AdminClinicCenter;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminClinicCenterRepository extends JpaRepository<AdminClinicCenter, Long> {

    User findOneByEmailAndPassword(String email, String password);
}
