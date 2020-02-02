package com.example.demo.service;

import com.example.demo.model.Holiday;
import com.example.demo.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    public Holiday save(Holiday holiday) {
        return this.holidayRepository.save(holiday);
    }

    public void delete(Long id) {
        this.holidayRepository.deleteHoliday(id);
    }
}
