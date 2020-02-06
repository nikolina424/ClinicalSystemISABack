package com.example.demo.service;

import com.example.demo.model.Clinic;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAll() {
        return this.roomRepository.findAll();
    }

    public Room save(Room room) {
        return this.roomRepository.save(room);
    }

    public Room findOneById(Long id) {
        return this.roomRepository.findOneById(id);
    }

    public void deleteRoom(Long id) {
        this.roomRepository.deleteRoom(id);
    }

    public List<Room> findAllByClinicIdAndNotReserved(Long id) {
        return this.roomRepository.findAllByClinicIdAndReserved(id, false);
    }

    public List<Room> findAllByDoctorId(Long id) {
        return this.roomRepository.findAllByDoctorId(id);
    }

    public List<Room> findAllByClinicId(Long id) {
        return this.roomRepository.findAllByClinicId(id);
    }

    public void setExToRoom(Long roomId, Long examinationId) {
        this.roomRepository.setExToRoom(roomId, examinationId);
    }

    public void setOpToRoom(Long roomId, Long operationId) {
        this.roomRepository.setOpToRoom(roomId, operationId);
    }

    public Room findRoomByExamination(Long id) {
        return this.roomRepository.findOneByExaminationId(id);
    }

    public Room findRoomByOperation(Long id) {
        return this.roomRepository.findOneByOperationId(id);
    }
}
