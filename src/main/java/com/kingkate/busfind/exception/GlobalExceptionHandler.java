package com.kingkate.busfind.exception;

import com.kingkate.busfind.constants.ResultCodeEnum;
import com.kingkate.busfind.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局拦截异常类
 * Created by yhang on 2019/4/24.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     *  拦截Exception类的异常
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResult exceptionHandler(Exception exception){
        log.error(exception.toString());
        return HttpResult.success(null, ResultCodeEnum.SYSTEM_ERROR.getCode(),ResultCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 自定义业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(BusFindException.class)
    @ResponseBody
    public HttpResult exceptionHandler(BusFindException exception){
        log.error(exception.getMsg());
        return HttpResult.success(null,exception.getCode(),exception.getMsg());
    }
}
