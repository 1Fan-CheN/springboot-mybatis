package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.sqldo.BaseProductDo;
import com.example.mapper.BaseModuleMapper;
import com.example.mapper.BaseProductMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseProductDao {

    @Resource
    private BaseProductMapper baseProductMapper;

    @Resource
    private BaseModuleMapper baseModuleMapper;

    public List<Object> listIds(String name, Integer status) {
        LambdaQueryWrapper<BaseProductDo> lambdaWrapper = new LambdaQueryWrapper<>();

        Map<SFunction<BaseProductDo, ?>, Object> map = new HashMap<>();
        map.put(BaseProductDo::getName, name);
        map.put(BaseProductDo::getStatus, status);

        lambdaWrapper.ne(BaseProductDo::getStatus, 3); // 不展示已刪除的 product
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

    public int getProductStatus(int productId) {
        LambdaQueryWrapper<BaseProductDo> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(BaseProductDo::getId, productId);
        lambdaWrapper.select(BaseProductDo::getStatus);
        return baseProductMapper.selectOne(lambdaWrapper).getStatus();
    }

    public void createProduct(BaseProductDo baseProductDo) {
        baseProductMapper.insert(baseProductDo);
    }

    public void updateProduct(BaseProductDo baseProductDo) {
        baseProductMapper.updateById(baseProductDo);
    }

    public void updateProductStatus(BaseProductDo baseProductDo) {
        baseProductMapper.updateById(baseProductDo);
    }

}
