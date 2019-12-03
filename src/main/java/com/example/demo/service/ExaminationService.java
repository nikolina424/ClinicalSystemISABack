package com.example.demo.service;

import com.example.demo.model.Examination;
import com.example.demo.repository.ExaminationRepository;
import com.example.demo.view.ExaminationViewSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationService {

    @Autowired
    private ExaminationRepository examinationRepository;

    public Examination save(ExaminationViewSchedule ex) {
        Examination examination = Examination.builder().description(ex.getDescription()).duration(ex.getDuration())
                                    .dateTime(ex.getDateTime()).build();

        return this.examinationRepository.save(examination);
    }
}
