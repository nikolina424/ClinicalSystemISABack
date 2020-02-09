package com.example.demo.view;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExaminationViewSchedule {

    private String description;
    private Double duration;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;
    private Double price;
    private User doctor;
    private Room room;
}
