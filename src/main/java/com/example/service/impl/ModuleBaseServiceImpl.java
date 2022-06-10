package com.example.service.impl;

import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.vo.BaseModuleVo;
import com.example.service.ModuleBaseService;
import com.example.utils.PermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class ModuleBaseServiceImpl implements ModuleBaseService {

    @Override
    public String createModule(BaseModuleVo baseModuleVo) {

        BaseModuleDo baseModuleDo = new BaseModuleDo();
        baseModuleDo.setName(baseModuleVo.getName());
        baseModuleDo.setDescription(baseModuleVo.getDesc());
        baseModuleDo.setCreateTime(new Date());
        baseModuleDo.setUpdateTime(new Date());
        baseModuleDo.setStatus(3);

        String sessionID = baseModuleVo.getSessionId();
        String operator = PermissionUtil.getOperatorBySessionID(sessionID);

        baseModuleDo.setCreator(operator);

        try {
//            return baseModuleMapper.createModule(baseModuleDo);
            return null;
        } catch (Exception e) {
            log.error(e.toString());
            return e.toString();
        }
    }

    @Override
    public boolean moduleNameRepeat(String name, int productId) {
        return false;
    }
}
