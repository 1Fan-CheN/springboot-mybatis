package com.example.service;

import com.example.entity.vo.BaseModuleVo;
import com.example.entity.vo.BaseProductVo;
import org.apache.ibatis.annotations.Mapper;

public interface PermissionService {

    boolean checkProductPermission(BaseProductVo baseProductVo);


    boolean checkModulePermission(BaseModuleVo baseModuleVo);
}
