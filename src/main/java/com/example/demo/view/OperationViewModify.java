package com.example.demo.view;

import com.example.demo.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationViewModify {

    private Long id;
    private String description;
    private Double price;
    private Double duration;
    private User doctor;
}
