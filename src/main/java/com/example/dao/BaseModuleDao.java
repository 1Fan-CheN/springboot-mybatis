package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.sqldo.BaseProductDo;
import com.example.entity.vo.ModuleOneIdVo;
import com.example.mapper.BaseModuleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseModuleDao {

    @Resource
    private BaseModuleMapper baseModuleMapper;

    public int getProductIdByModuleId(int moduleId){
        LambdaQueryWrapper<BaseModuleDo> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(BaseModuleDo::getId, moduleId);
        return baseModuleMapper.selectOne(lambdaWrapper).getProductId();
    }

    public int getModuleStatus(int id){
        LambdaQueryWrapper<BaseModuleDo> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(BaseModuleDo::getId, id);
        lambdaWrapper.select(BaseModuleDo::getStatus);
        return baseModuleMapper.selectOne(lambdaWrapper).getStatus();
    }

    public void updateModuleStatus(BaseModuleDo baseModuleDo) {
        baseModuleMapper.updateById(baseModuleDo);
    }

    public int countToken(String token){
        LambdaQueryWrapper<BaseModuleDo> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(BaseModuleDo::getToken, token);
        return baseModuleMapper.selectCount(lambdaWrapper);
    }

    public List<Object> listIds(String name, String token, Integer status) {
        LambdaQueryWrapper<BaseModuleDo> lambdaWrapper = new LambdaQueryWrapper<>();

        Map<SFunction<BaseModuleDo, ?>, Object> map = new HashMap<>();
        map.put(BaseModuleDo::getName, name);
        map.put(BaseModuleDo::getToken, token);
        map.put(BaseModuleDo::getStatus, status);

        lambdaWrapper.ne(BaseModuleDo::getStatus, 2); // 不展示已刪除的 module
        lambdaWrapper.allEq(map, false); // null2IsNull 设为 false 表示忽视入参为 null 的条件判断

        lambdaWrapper.orderByDesc(BaseModuleDo::getUpdateTime);
        // select ID
        lambdaWrapper.select(BaseModuleDo::getId);

        return baseModuleMapper.selectObjs(lambdaWrapper);
    }

    public void createModule(BaseModuleDo baseModuleDo){
        baseModuleMapper.insert(baseModuleDo);
    }

    public void updateProduct(BaseModuleDo baseModuleDo){
        baseModuleMapper.updateById(baseModuleDo);
    }
}
