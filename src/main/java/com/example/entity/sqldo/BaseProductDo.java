package com.example.entity.sqldo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "product_table", autoResultMap = true)
public class BaseProductDo extends BaseDo {
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> owner;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> administrator;
}
