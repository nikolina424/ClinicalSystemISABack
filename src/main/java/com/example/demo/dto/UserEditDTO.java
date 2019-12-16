package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEditDTO {

    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private Integer userId;
    private String address;
    private String city;
    private String country;
}
