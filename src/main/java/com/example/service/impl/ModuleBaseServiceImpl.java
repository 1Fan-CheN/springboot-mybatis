package com.example.service.impl;

import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.vo.BaseModuleVo;
import com.example.mapper.BaseModuleMapper;
import com.example.service.ModuleBaseService;
import com.example.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class ModuleBaseServiceImpl implements ModuleBaseService {

    @Resource
    private BaseModuleMapper baseModuleMapper;

    @Override
    public String createModule(BaseModuleVo baseModuleVo) {

        BaseModuleDo baseModuleDo = new BaseModuleDo();
        baseModuleDo.setName(baseModuleVo.getName());
        baseModuleDo.setDesc(baseModuleVo.getDesc());
        baseModuleDo.setCreateTime(new Date());
        baseModuleDo.setUpdateTime(new Date());
        baseModuleDo.setStatus(3);

        String sessionID = baseModuleVo.getSessionId();
        String operator = UserUtil.getOperatorBySessionID(sessionID);

        baseModuleDo.setCreator(operator);

        try {
            return baseModuleMapper.createModule(baseModuleDo);
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
