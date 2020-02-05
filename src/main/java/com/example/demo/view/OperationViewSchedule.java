package com.example.demo.view;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationViewSchedule {

    private String description;
    private Integer duration;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;
    private Double price;
    private User doctor;
    private Room room;
}
