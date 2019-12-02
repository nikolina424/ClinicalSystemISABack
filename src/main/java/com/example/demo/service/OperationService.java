package com.example.demo.service;

import com.example.demo.model.Operation;
import com.example.demo.repository.OperationRepository;
import com.example.demo.view.OperationViewSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public Operation save(OperationViewSchedule op) {
        Operation operation = Operation.builder().description(op.getDescription()).duration(op.getDuration())
                                .dateTime(op.getDateTime()).build();

        return this.operationRepository.save(operation);
    }
}
