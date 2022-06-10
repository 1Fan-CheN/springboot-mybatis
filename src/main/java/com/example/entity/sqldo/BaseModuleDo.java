package com.example.entity.sqldo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "model_module_table", autoResultMap = true)
public class BaseModuleDo extends BaseDo {
    private int productId;
    private String token;
}
