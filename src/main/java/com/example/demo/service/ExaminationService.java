package com.example.demo.service;

import com.example.demo.model.Examination;
import com.example.demo.model.User;
import com.example.demo.repository.ExaminationRepository;
import com.example.demo.view.ExaminationViewSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationService {

    @Autowired
    private ExaminationRepository examinationRepository;

    public Examination findOneById(Long id) {
        return this.examinationRepository.findOneById(id);
    }

    public List<Examination> findAll() {
        return this.examinationRepository.findAll();
    }

    public Examination save(Examination ex) {
        return this.examinationRepository.save(ex);
    }

    public Examination save(ExaminationViewSchedule ex) {
        Examination examination = Examination.builder().description(ex.getDescription()).duration(ex.getDuration())
                                    .dateTime(ex.getDateTime()).doctor(ex.getDoctor()).price(ex.getPrice()).build();

        return this.examinationRepository.save(examination);
    }

    public Examination saveDoctor(ExaminationViewSchedule ex, User doctor) {
        Examination examination = Examination.builder().description(ex.getDescription()).duration(ex.getDuration())
                .dateTime(ex.getDateTime()).doctor(doctor).price(ex.getPrice()).build();

        return this.examinationRepository.save(examination);
    }

    public void deleteExamination(Long id) {
        this.examinationRepository.deleteExamination(id);
    }

    public void deleteExaminationRoom(Long id) {
        this.examinationRepository.deleteExaminationRoom(id);
    }

    public List<Examination> findExaminationsOfClinic(Long id) {
        return this.examinationRepository.findAllExaminationsOfClinic(id);
    }
}
