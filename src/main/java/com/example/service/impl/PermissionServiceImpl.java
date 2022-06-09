package com.example.service.impl;

import com.example.entity.vo.BaseModuleVo;
import com.example.entity.vo.BaseProductVo;
import com.example.mapper.BaseProductMapper;
import com.example.service.PermissionService;
import com.example.utils.UserUtil;

import javax.annotation.Resource;

public class PermissionServiceImpl implements PermissionService {

    @Resource
    private BaseProductMapper baseProductMapper;

    @Override
    public boolean checkProductPermission(BaseProductVo baseProductVo) {
        String operator = UserUtil.getOperatorBySessionID(baseProductVo.getSessionId());
        return checkOperatorPermission(operator, baseProductVo.getId());
    }

    @Override
    public boolean checkModulePermission(BaseModuleVo baseModuleVo){
        String operator = UserUtil.getOperatorBySessionID(baseModuleVo.getSessionId());
        return checkOperatorPermission(operator, baseModuleVo.getProductId());
    }

    public boolean checkOperatorPermission(String operator, int productId){
        try{
            // TODO check db by operator and product id
        }catch (Exception e){

        }
        return false;
    }

}
