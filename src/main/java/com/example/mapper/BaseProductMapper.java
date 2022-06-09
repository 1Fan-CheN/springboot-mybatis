package com.example.mapper;

import com.example.entity.sqldo.BaseProductDo;
import com.example.mapper.provider.BaseProductProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BaseProductMapper {

    @SelectProvider(type = BaseProductProvider.class, method = "getIdList")
    List<Integer> getIdList(String name, int status, String owner);

    @InsertProvider(type = BaseProductProvider.class, method = "createProduct")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createProduct(BaseProductDo baseProductDo);

    @Select("SELECT count(id) FROM product_table WHERE name=#{name}")
    int countName(@Param(value = "name") String name);

    @Select("SELECT count(id) FROM model_module_table WHERE productId=#{productId} AND status != 2")
    int countUndeletedModule(@Param(value = "productId") int productId);


    @Insert("INSERT INTO product_table(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    String testInsert(BaseProductDo baseProductDo);
}
