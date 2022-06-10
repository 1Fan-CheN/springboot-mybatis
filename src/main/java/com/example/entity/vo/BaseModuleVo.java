package com.example.entity.vo;

import lombok.Data;

@Data
public class BaseModuleVo {
    private Integer id;
    private String sessionId;
    private int productId;
    private String name;
    private String desc;
    private String token;
}
