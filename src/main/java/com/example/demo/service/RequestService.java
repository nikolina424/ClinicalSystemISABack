package com.example.demo.service;

import com.example.demo.model.Request;
import com.example.demo.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    private JavaMailSender javaMailSender;

    @Autowired
    public RequestService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public List<Request> findAll() {
        return this.requestRepository.findAll();
    }

    public Request save(Request request) {
        return this.requestRepository.save(request);
    }

    public void delete(Long id) {
        this.requestRepository.deleteRequest(id);
    }
}
