package com.example.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class BaseProductVo {
    private String sessionId;
    private String name;
    private String desc;
    private List<String> owner;
    private List<String> admin;
    private Integer status;
}
