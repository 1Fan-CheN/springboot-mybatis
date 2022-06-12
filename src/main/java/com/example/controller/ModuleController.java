package com.example.controller;

import com.example.entity.vo.ModuleOneIdVo;
import com.example.entity.vo.BaseModuleResp;
import com.example.service.ModuleBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/v1/module")
public class ModuleController {

    @Resource
    private ModuleBaseService moduleBaseService;

    @PostMapping("create")
    public BaseModuleResp CreateModule(@RequestBody ModuleOneIdVo moduleOneIdVo) {
        return moduleBaseService.createModule(moduleOneIdVo);
    }

    @PostMapping("update")
    public BaseModuleResp OfflineModule(@RequestBody ModuleOneIdVo moduleOneIdVo) {
        return moduleBaseService.updateModule(moduleOneIdVo);
    }

    @PostMapping("delete")
    public BaseModuleResp DeleteModule() {
        return null;
    }

}
