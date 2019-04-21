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
        Object retVal;
        try {
            retVal = joinPoint.proceed();
        } catch (Throwable throwable) {
            //响应数据执行异常时输出
            log.info("请求地址: " + request.getRequestURL().toString());
            log.info("请求方式: " + request.getMethod());
            log.info("请求IP: " + request.getRemoteAddr());
            log.info("请求方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.info("请求参数: " + JSON.toJSONString(joinPoint.getArgs()));
            //结束计时
            stopWatch.stop();
            log.info("执行时间: ms " + stopWatch.getTime());
            throw throwable;
        }
        // 记录下响应内容
        log.info("请求地址: " + request.getRequestURL().toString());
        log.info("请求方式: " + request.getMethod());
        log.info("请求IP: " + request.getRemoteAddr());
        log.info("请求方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数: " + JSON.toJSONString(joinPoint.getArgs()));
        //结束计时
        stopWatch.stop();
        log.info("执行时间: " + stopWatch.getTime()+" ms");
        log.info("响应数据: " + JSON.toJSON(retVal));
        return retVal;
    }

}