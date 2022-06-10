package com.example.entity.sqldo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "update_time")
    private Date updateTime;
    private String name;
    private String description;
    private Integer status;
    private String creator;
}