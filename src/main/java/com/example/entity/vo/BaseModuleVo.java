package com.example.entity.vo;

import lombok.Data;

@Data
public class BaseModuleVo extends BaseVo {
    private int productId;
    private String name;
    private String desc;
    private String token;
}
