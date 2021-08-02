package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class score {
    private int id;
    private int s_id;
    private int c_id;
    private int score;
    private int time;
}
