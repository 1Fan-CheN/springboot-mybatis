package com.example.entity.sqldo;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDo {
    private int id;
    private Date createTime;
    private Date updateTime;
    private String name;
    private String desc;
    private Integer status;
    private String creator;
}