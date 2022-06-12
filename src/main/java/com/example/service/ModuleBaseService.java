package com.example.service;

import com.example.entity.vo.ModuleOneIdVo;
import com.example.entity.vo.BaseModuleResp;

public interface ModuleBaseService {

    boolean checkModulePermission(String sessionId, int moduleId);

    boolean tokenRepeat(String token);

    int getModuleStatus(Integer id);

    void updateModuleStatus(int moduleId, int stauts);

    BaseModuleResp listModuleIds(ModuleOneIdVo moduleOneIdVo);


    BaseModuleResp createModule(ModuleOneIdVo moduleOneIdVo);

    BaseModuleResp updateModule(ModuleOneIdVo moduleOneIdVo);

    BaseModuleResp deleteModule(ModuleOneIdVo moduleOneIdVo);

}
