package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.sqldo.BaseProductDo;
import com.example.mapper.BaseModuleMapper;

import javax.annotation.Resource;

public class BaseModuleDao {

    @Resource
    private BaseModuleMapper baseModuleMapper;

    public int countToken(String token){
        LambdaQueryWrapper<BaseModuleDo> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(BaseModuleDo::getToken, token);
        return baseModuleMapper.selectCount(lambdaWrapper);
    }

    public void createModule(BaseModuleDo baseModuleDo){
        baseModuleMapper.insert(baseModuleDo);
    }

    public void updateProduct(BaseModuleDo baseModuleDo){
        baseModuleMapper.updateById(baseModuleDo);
    }
}
