package com.example.mapper.provider;

import com.example.entity.sqldo.BaseProductDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

public class BaseProductProvider {

    public String getIdList(String name, int status, String owner) {
        SQL sql = new SQL();

        sql.SELECT("id");
        sql.FROM("product_table");
        if (name != null) {
            sql.SET("name=#{name}");
        }
        if (status != 0) {
            sql.SET("status=#{status}");
        }
        if (owner != null) {
            sql.SET("owner=#{owner}");
        }

        return sql.toString();
    }

    public String createProduct(BaseProductDo baseProductDo) {
        SQL sql = new SQL();

        sql.INSERT_INTO("product_table");
        sql.VALUES("createTime", "#{createTime}");
        sql.VALUES("updateTime", "#{updateTime}");
        sql.VALUES("name", "#{name}");
        sql.VALUES("desc", "#{desc}");
        sql.VALUES("status", "#{status}");
        sql.VALUES("creator", "#{creator}");
        sql.VALUES("owner", "#{owner}");
        sql.VALUES("admin", "#{admin}");

        return sql.toString();
    }


}
