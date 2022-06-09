package com.example.entity.vo;

import com.example.entity.sqldo.BaseDo;
import lombok.Data;

@Data
public class BaseProductVo extends BaseVo {
    private String name;
    private String desc;
    private String owner;
    private String admin;
    private int status;
}
