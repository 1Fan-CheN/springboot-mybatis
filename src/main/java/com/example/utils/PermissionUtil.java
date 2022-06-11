package com.example.utils;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.sqldo.BaseProductDo;
import com.example.mapper.BaseProductMapper;

import javax.annotation.Resource;

public class PermissionUtil {

    public static String getOperatorBySessionID(String sessionID) {
        // TODO
        return "sa";
    }

}
