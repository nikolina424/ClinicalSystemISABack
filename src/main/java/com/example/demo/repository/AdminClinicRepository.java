package com.example.demo.repository;

import com.example.demo.model.AdminClinic;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminClinicRepository extends JpaRepository<AdminClinic, Long> {

    User findOneByEmailAndPassword(String email, String password);
}
