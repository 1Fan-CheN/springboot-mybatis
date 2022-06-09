package com.example.mapper;

import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.sqldo.BaseProductDo;

import com.example.mapper.provider.BaseModuleProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface BaseModuleMapper {

    @Select("SELECT productId FROM model_module_table WHERE moduleId=#{moduleId}")
    int getProductIdByModuleId(int moduleId);

    @InsertProvider(type = BaseModuleProvider.class, method = "createModule")
    String createModule(BaseModuleDo baseModuleDo);

    @Select("SELECT count(id) FROM model_module_table WHERE productId=#{productId} AND name=#{name}")
    int countName(String name, int productId);

}
