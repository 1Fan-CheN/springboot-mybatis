package com.example.domain.base;

import lombok.Data;

@Data
public class BaseProductBo {
    private String name;
    private String desc;
    private String owner;
    private String admin;
    private Integer status;
    private String creator;
}
