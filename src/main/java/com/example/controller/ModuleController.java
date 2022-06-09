package com.example.controller;

import com.example.entity.vo.BaseModuleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.vo.BaseModuleResp;


@Slf4j
@RestController
@RequestMapping("/v1/module")
public class ModuleController {

    @PostMapping("create")
    public BaseModuleResp CreateModule(@RequestBody BaseModuleVo request){
        log.info("testing...");
        return new BaseModuleResp().success("success");
    }

    @PostMapping("offline")
    public BaseModuleResp OfflineModule(){
        log.info("testing222...");
        return null;
    }

    @PostMapping("delete")
    public BaseModuleResp DeleteModule() {
        return null;
    }

}
