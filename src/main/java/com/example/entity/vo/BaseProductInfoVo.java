package com.example.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class BaseProductInfoVo {
    private List<Integer> id;
    private String sessionId;
    private String name;
    private List<String> owner;
    private Integer status;
}
