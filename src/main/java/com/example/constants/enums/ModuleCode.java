package com.example.constants.enums;

public enum ModuleCode {
    SUCCESS(0, "success"),
    UNKNOWN_ERROR(-1, "unknown error"),
    PRODUCT_NOT_REGISTERED(1, "product doesn't be registered"),
    NO_PERMISSION(2, "don't have permission to operate"),
    TOKEN_NOT_UNIQUE(3, "token is not unique");

    private final Integer code;
    private final String msg;

    ModuleCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
