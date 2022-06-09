package com.example.service;

import com.example.entity.vo.BaseModuleVo;
import com.example.entity.vo.BaseProductVo;

public interface ModuleBaseService {
    String createModule(BaseModuleVo baseModuleVo);

    boolean moduleNameRepeat(String name, int productId);

}
