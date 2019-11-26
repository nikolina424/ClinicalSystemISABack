package com.example.demo.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserViewChangePassword {

    private String oldPass;
    private String newPass;
    private String newRepeatPass;
}
