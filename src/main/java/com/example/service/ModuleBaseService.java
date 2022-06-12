package com.example.service;

import com.example.entity.vo.ModuleOneIdVo;
import com.example.entity.vo.BaseModuleResp;

public interface ModuleBaseService {

    boolean checkModulePermission(String sessionId, int moduleId);

    boolean tokenRepeat(String token);

    int getModuleStatus(Integer id);

    BaseModuleResp createModule(ModuleOneIdVo moduleOneIdVo);

    BaseModuleResp updateModule(ModuleOneIdVo moduleOneIdVo);

}
