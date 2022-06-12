package com.example.entity.vo;

import lombok.Data;

@Data
public class BaseModuleVo {
    private String sessionId;
    private Integer productId;
    private String name;
    private String desc;
    private String token;
    private int status;
}
