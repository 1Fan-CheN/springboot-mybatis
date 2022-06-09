package com.example.entity.sqldo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class BaseProductDo extends BaseDo {
    private String owner;
    private String admin;

}
