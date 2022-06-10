package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.sqldo.BaseProductDo;
import com.example.mapper.BaseModuleMapper;
import com.example.mapper.BaseProductMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class BaseProductDao {

    @Resource
    private BaseProductMapper baseProductMapper;

    @Resource
    private BaseModuleMapper baseModuleMapper;

    public List<Object> getIdList(String name, Integer status) {
        LambdaQueryWrapper<BaseProductDo> lambdaWrapper = new LambdaQueryWrapper<>();

        Map<SFunction<BaseProductDo, ?>, Object> map = new HashMap<>();
        map.put(BaseProductDo::getName, name);
        map.put(BaseProductDo::getStatus, status);

        lambdaWrapper.allEq(map, false); // null2IsNull 设为 false 表示忽视入参为 null 的条件判断

        lambdaWrapper.orderByDesc(BaseProductDo::getUpdateTime);
        // select ID
        lambdaWrapper.select(BaseProductDo::getId);

        return baseProductMapper.selectObjs(lambdaWrapper);
    }

    public List<Map<String, Object>> getProductInfoById(List<Integer> idList) {
        LambdaQueryWrapper<BaseProductDo> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.in(BaseProductDo::getId, idList);
        return baseProductMapper.selectMaps(lambdaWrapper);
    }

    public int countName(String name) {
        LambdaQueryWrapper<BaseProductDo> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(BaseProductDo::getName, name);
        return baseProductMapper.selectCount(lambdaWrapper);
    }

    public int countUndeletedModule(int productId) {
        LambdaQueryWrapper<BaseModuleDo> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(BaseModuleDo::getProductId, productId);
        lambdaWrapper.ne(BaseModuleDo::getStatus, 2); // status=2 已删除
        return baseModuleMapper.selectCount(lambdaWrapper);
    }

    public Integer createProduct(BaseProductDo baseProductDo){
        return baseProductMapper.insert(baseProductDo);
    }

    public Integer updateProduct(BaseProductDo baseProductDo){
        return baseProductMapper.updateById(baseProductDo);
    }

}
