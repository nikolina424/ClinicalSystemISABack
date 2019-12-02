package com.example.demo.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationViewSchedule {

    private String description;
    private Integer duration;
    private java.util.Calendar dateTime;
}
