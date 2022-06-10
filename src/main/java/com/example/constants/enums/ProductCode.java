package com.example.constants.enums;

public enum ProductCode {
    SUCCESS(0, "success"),
    UNKNOWN_ERROR(-1, "unknown error"),
    MODEL_NOT_OFFLINE(1, "not all modules offline"),
    NO_PERMISSION(2, "don't have permission to operate"),
    NAME_NOT_ONLY(3, "product name is not unique");

    private final Integer code;
    private final String msg;

    ProductCode(Integer code, String msg) {
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
