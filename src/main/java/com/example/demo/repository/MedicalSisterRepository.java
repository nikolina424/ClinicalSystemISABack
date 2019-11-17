package com.example.demo.repository;

import com.example.demo.model.MedicalSister;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalSisterRepository extends JpaRepository<MedicalSister, Long> {

    User findOneByEmailAndPassword(String email, String password);
}
