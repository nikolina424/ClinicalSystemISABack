package com.example.demo.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicViewModify {

    private Long clinicId;
    private String name;
    private String description;
    private String address;
    private float longitude;
    private float latitude;
}
