package com.example.service;

import com.example.entity.vo.BaseModuleVo;
import com.example.entity.vo.BaseProductVo;

public interface PermissionService {

    boolean checkProductPermission(BaseProductVo baseProductVo);

    boolean checkModulePermission(BaseModuleVo baseModuleVo);
}
