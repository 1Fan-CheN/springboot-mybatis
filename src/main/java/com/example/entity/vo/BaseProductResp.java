package com.example.entity.vo;

import com.example.constants.enums.ModuleCode;
import lombok.Data;

@Data
public class BaseProductResp {
    private String msg;
    private int code;
    private Object data;

    public BaseProductResp success(Object data) {
        BaseProductResp resp = new BaseProductResp();
        resp.setCode(ModuleCode.SUCCESS.getCode());
        resp.setMsg(ModuleCode.SUCCESS.getMsg());
        resp.setData(data);
        return resp;
    }

    public BaseProductResp fail() {
        Integer code = ModuleCode.UNKNOWN_ERROR.getCode();
        String msg = ModuleCode.UNKNOWN_ERROR.getMsg();
        return fail(code, msg, "unknown error");
    }

    public BaseProductResp fail(Integer code, String msg, Object data) {
        BaseProductResp resp = new BaseProductResp();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setData(data);
        return resp;
    }
}
