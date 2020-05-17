package com.sane.so2o.enums;

import lombok.Data;

public enum  RetCodeEnum {
    SUCCESS("200","成功"),FAIL("500","失败");
    private String code;
    private String message;

    private RetCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
