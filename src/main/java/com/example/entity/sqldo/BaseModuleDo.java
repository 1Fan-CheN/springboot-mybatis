package com.example.entity.sqldo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class BaseModuleDo extends BaseDo {
    private int productId;
    private String token;
}
