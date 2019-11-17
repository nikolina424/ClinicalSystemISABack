package com.example.demo.service;

import com.example.demo.model.MedicalSister;
import com.example.demo.model.User;
import com.example.demo.repository.MedicalSisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalSisterService {
    @Autowired
    private MedicalSisterRepository medicalSisterRepository;

//    public MedicalSister findOne(Long id) {
//        return medicalSisterRepository.findById(id).orElseGet(null);
//    }

    public List<MedicalSister> findAll() {
        return medicalSisterRepository.findAll();
    }

    public Page<MedicalSister> findAll(Pageable page) {
        return medicalSisterRepository.findAll(page);
    }

    public MedicalSister save(MedicalSister medicalSister) {
        return medicalSisterRepository.save(medicalSister);
    }

//    public void remove(Long id) {
//        medicalSisterRepository.deleteById(id);
//    }

    public User findOneByEmailAndPassword(String email, String password) {
        return this.medicalSisterRepository.findOneByEmailAndPassword(email, password);
    }
}
