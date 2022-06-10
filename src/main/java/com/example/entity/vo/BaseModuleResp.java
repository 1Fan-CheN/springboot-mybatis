package com.example.entity.vo;

import com.example.constants.enums.ModuleCode;
import lombok.Data;

@Data
public class BaseModuleResp {
    private String msg;
    private int code;
    private Object data;

    public BaseModuleResp success(Object data) {
        BaseModuleResp resp = new BaseModuleResp();
        resp.setCode(ModuleCode.SUCCESS.getCode());
        resp.setMsg(ModuleCode.SUCCESS.getMsg());
        resp.setData(data);
        return resp;
    }

    public BaseModuleResp fail() {
        Integer code = ModuleCode.UNKNOWN_ERROR.getCode();
        String msg = ModuleCode.UNKNOWN_ERROR.getMsg();
        return fail(code, msg, "unknown error");
    }

    public BaseModuleResp fail(Integer code, String msg, Object data) {
        BaseModuleResp resp = new BaseModuleResp();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setData(data);
        return resp;
    }
}
