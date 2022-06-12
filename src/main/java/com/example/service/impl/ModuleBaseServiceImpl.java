package com.example.service.impl;

import com.example.dao.BaseModuleDao;
import com.example.entity.sqldo.BaseModuleDo;
import com.example.entity.vo.*;
import com.example.service.ModuleBaseService;
import com.example.utils.PermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class ModuleBaseServiceImpl implements ModuleBaseService {

    @Resource
    private BaseModuleDao baseModuleDao;

    @Override
    public boolean checkModulePermission(String sessionId, int moduleId){
        String operator = PermissionUtil.getOperatorBySessionID(sessionId);

        return true; //TODO
    }

    @Override
    public boolean tokenRepeat(String token){
        return baseModuleDao.countToken(token) != 0;
    }

    @Override
    public int getModuleStatus(Integer id){
        return 1; // TODO
    }

    @Override
    public BaseModuleResp createModule(ModuleOneIdVo moduleOneIdVo) {

        if (tokenRepeat(moduleOneIdVo.getToken())){
            return new BaseModuleResp().fail(-1, "token repeat", null); // TODO code
        }

        BaseModuleDo baseModuleDo = new BaseModuleDo();
        baseModuleDo.setName(moduleOneIdVo.getName());
        baseModuleDo.setDescription(moduleOneIdVo.getDesc());
        baseModuleDo.setToken(moduleOneIdVo.getToken());
        baseModuleDo.setCreateTime(new Date());
        baseModuleDo.setUpdateTime(new Date());
        baseModuleDo.setStatus(3);

        String sessionID = moduleOneIdVo.getSessionId();
        String operator = PermissionUtil.getOperatorBySessionID(sessionID);

        baseModuleDo.setCreator(operator);

        try {
            baseModuleDao.createModule(baseModuleDo);
            return new BaseModuleResp().success(null);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseModuleResp().fail(-1, "db insert error", e.toString());
        }
    }

    @Override
    public BaseModuleResp updateModule(ModuleOneIdVo moduleOneIdVo) {
        if (!checkModulePermission(moduleOneIdVo.getSessionId(), moduleOneIdVo.getId())){
            return new BaseModuleResp().fail(2, "no permission to operate", null);
        }
        if (moduleOneIdVo.getToken() != null){
            if(tokenRepeat(moduleOneIdVo.getToken())){
                return new BaseModuleResp().fail(); //TODO
            }
        }
        BaseModuleDo baseModuleDo = new BaseModuleDo();
        baseModuleDo.setToken(moduleOneIdVo.getToken());
        // field-strategy 默认为 not_null 判断，即只更新和插入非NULL值（不包括非空值）
        baseModuleDo.setUpdateTime(new Date());
        baseModuleDo.setId(moduleOneIdVo.getId());
        baseModuleDo.setName(moduleOneIdVo.getName());
        baseModuleDo.setDescription(moduleOneIdVo.getDesc());
        baseModuleDo.setStatus(moduleOneIdVo.getStatus());

        try {
            baseModuleDao.updateProduct(baseModuleDo);
            return new BaseModuleResp().success(null);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseModuleResp().fail(-1, "db update error", e.toString());
        }
    }

}
