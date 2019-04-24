package com.kingkate.busfind.constants;

import lombok.Getter;

/**
 * 异常状态码定义枚举类
 * @author yhang
 * @Date 2019/4/24.
 */
@Getter
public enum ResultCodeEnum {

    //系统异常
    SYSTEM_ERROR(500,"system error"),
    //success
    SUCCESS(200,"success");
    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
