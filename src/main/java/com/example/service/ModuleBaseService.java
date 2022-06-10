package com.example.service;

import com.example.entity.vo.BaseModuleVo;

public interface ModuleBaseService {
    String createModule(BaseModuleVo baseModuleVo);

    boolean moduleNameRepeat(String name, int productId);

}
