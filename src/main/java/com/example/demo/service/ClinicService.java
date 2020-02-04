package com.example.demo.service;

import com.example.demo.model.Clinic;
import com.example.demo.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public List<Clinic> getClinics(){
        return this.clinicRepository.findAll();
    }

    public Clinic findOneByName(String name) {
        return this.clinicRepository.findOneByName(name);
    }

    public Clinic findOneByAdminId(Long id) {
        return this.clinicRepository.findOneByAdminId(id);
    }

    public Clinic findOneById(Long id) {
        return this.clinicRepository.findOneById(id);
    }

    public Clinic save(Clinic clinic) {
        return this.clinicRepository.save(clinic);
    }
}
