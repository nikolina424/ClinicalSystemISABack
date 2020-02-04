package com.example.demo.service;

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
}
