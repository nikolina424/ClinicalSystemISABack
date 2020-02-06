package com.example.demo.service;

import com.example.demo.model.Operation;
import com.example.demo.model.User;
import com.example.demo.repository.OperationRepository;
import com.example.demo.view.OperationViewSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> findAll() {
        return this.operationRepository.findAll();
    }

    public Operation findOneById(Long id) {
        return this.operationRepository.findOneById(id);
    }

    public Operation save(Operation op) {
        return this.operationRepository.save(op);
    }

    public Operation save(OperationViewSchedule op) {
        Operation operation = Operation.builder().description(op.getDescription()).duration(op.getDuration())
                                .dateTime(op.getDateTime()).doctor(op.getDoctor()).price(op.getPrice()).build();

        return this.operationRepository.save(operation);
    }

    public Operation saveDoctor(OperationViewSchedule op, User doctor) {
        Operation operation = Operation.builder().description(op.getDescription()).duration(op.getDuration())
                .dateTime(op.getDateTime()).doctor(doctor).price(op.getPrice()).build();

        return this.operationRepository.save(operation);
    }

    public void deleteOperation(Long id) {
        this.operationRepository.deleteOperation(id);
    }

    public void deleteOperationRoom(Long id) {
        this.operationRepository.deleteOperationRoom(id);
    }

    public List<Operation> findOperationsOfClinic(Long id) {
        return this.operationRepository.findAllOperationsOfClinic(id);
    }
}
