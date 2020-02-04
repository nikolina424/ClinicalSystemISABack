package com.example.demo.view;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomViewModify {

    private Long roomId;
    private Integer number;
    private String clinicName;
}
