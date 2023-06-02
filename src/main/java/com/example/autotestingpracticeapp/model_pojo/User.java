package com.example.autotestingpracticeapp.model_pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String u_login;
    private String email;
}
