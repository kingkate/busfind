package com.kingkate.busfind.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("execution(* com.kingkate.busfind.controller..*.*(..))")
    public void log() {
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //开始计时
        StopWatch stopWatch = StopWatch.createStarted();
        // 记录下请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object retVal = null;
        try {
            retVal = joinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        }finally {
            // 记录下响应内容
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("请求地址: " + request.getRequestURL().toString()).append(" === 请求方式: " + request.getMethod()).append(" === 请求IP: " + request.getRemoteAddr()).append(" === 请求方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()).append(" === 请求参数: " + JSON.toJSONString(joinPoint.getArgs()));
            //结束计时
            stopWatch.stop();
            stringBuilder.append(" === 执行时间: " + stopWatch.getTime()+" ms").append(" === 响应数据: " + JSON.toJSON(retVal));
            log.info(stringBuilder.toString());
        }
        return retVal;
    }

}