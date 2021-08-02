package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class teacher {
    private int id;
    private String name;
    private int age;
    private int sex;
    private String course;
}
