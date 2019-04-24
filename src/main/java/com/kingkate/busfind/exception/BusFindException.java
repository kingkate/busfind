package com.kingkate.busfind.exception;

import com.kingkate.busfind.constants.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常类型
 * @Author: yhang
 * @Date: 2019/4/24 16:20
 */
@Data
public class BusFindException extends RuntimeException {
    private Integer code;
    private String msg;

    public BusFindException(ResultCodeEnum result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
    }
}
