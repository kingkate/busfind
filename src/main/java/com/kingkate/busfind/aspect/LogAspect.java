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
    
    public final static String spliter = " === ";


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
        writeRquestLog(joinPoint,request);
        try {
            retVal = joinPoint.proceed();
        } catch (Throwable throwable) {
            //响应数据执行异常时输出

            //结束计时
            stopWatch.stop();
            writeResponseLog(joinPoint,request,null,stopWatch);
            throw throwable;
        }
        // 记录下响应内容
        //结束计时
        stopWatch.stop();
        writeResponseLog(joinPoint, request,retVal,stopWatch);
        return retVal;
    }

    private void writeResponseLog(ProceedingJoinPoint joinPoint, HttpServletRequest request, Object retVal, StopWatch stopWatch) {
        StringBuffer buffer = new StringBuffer("");
        buffer.append("请求地址: " + request.getRequestURL().toString()).append(spliter);
        buffer.append("请求方式: " + request.getMethod()).append(spliter);
        buffer.append("请求IP: " + request.getRemoteAddr()).append(spliter);
        buffer.append("请求方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()).append(spliter);
        buffer.append("请求参数: " + JSON.toJSONString(joinPoint.getArgs())).append(spliter);
        buffer.append("响应数据：" + JSON.toJSON(retVal)).append(spliter);
        buffer.append("执行时间：" + stopWatch.getTime() + "ms");
        log.info(buffer.toString());

    }

    private void writeRquestLog(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("");
        buffer.append("请求地址: " + request.getRequestURL().toString()).append(spliter);
        buffer.append("请求方式: " + request.getMethod()).append(spliter);
        buffer.append("请求IP: " + request.getRemoteAddr()).append(spliter);
        buffer.append("请求方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()).append(spliter);
        buffer.append("请求参数: " + JSON.toJSONString(joinPoint.getArgs()));
        log.info(buffer.toString());
    }

}