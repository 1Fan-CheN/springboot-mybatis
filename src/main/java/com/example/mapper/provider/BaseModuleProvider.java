package com.example.mapper.provider;

import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.sqldo.BaseProductDo;
import com.example.mapper.BaseModuleMapper;
import com.example.mapper.BaseProductMapper;
import org.apache.ibatis.jdbc.SQL;


public class BaseModuleProvider {

    public String createModule(BaseModuleDo baseModuleDo) {
        SQL sql = new SQL();
        sql.INSERT_INTO("model_module_table");

        sql.VALUES("createTime", "#{createTime}");
        sql.VALUES("updateTime", "#{updateTime}");
        sql.VALUES("name", "#{name}");
        sql.VALUES("desc", "#{desc}");
        sql.VALUES("status", "#{status}");
        sql.VALUES("creator", "#{creator}");

        sql.VALUES("token", "#{token}");
        sql.VALUES("productId", "#{productId}");

        return sql.toString();
    }

}
