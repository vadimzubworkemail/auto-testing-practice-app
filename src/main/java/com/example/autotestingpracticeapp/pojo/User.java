package com.example.autotestingpracticeapp.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class User {

    private UUID id;
    private String login;
    private String email;

}
