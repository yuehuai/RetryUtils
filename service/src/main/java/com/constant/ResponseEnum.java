package com.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(0, "成功"),
    ERROR(500, "失败");

    private Integer code;
    private String message;

}
