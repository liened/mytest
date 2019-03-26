package com.exm.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@Aspect
@Slf4j
public class MyAspect {

    @Around(value = "@annotation(com.exm.common.Log)")
    public Object execTime(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        //参数
        Object[] paramValues = pjp.getArgs();
        String[] parameterNames = ((CodeSignature) pjp.getSignature()).getParameterNames();
        StringBuffer sb = new StringBuffer("{");
        for (int i=0;i<parameterNames.length;i++){
            sb.append(parameterNames[i]).append(":").append(paramValues[i]);
            if (i!=parameterNames.length-1){
                sb.append(",");
            }
        }
        sb.append("}");

        long start = Clock.systemDefaultZone().millis();
        Object returnVal = pjp.proceed();
        long end = Clock.systemDefaultZone().millis();
        log.info("*********************");
        log.info("执行 {}.{} 方法，共用了{}毫秒，参数:{},返回值:{}",className,methodName,(end-start),sb.toString(),returnVal);
        log.info("*********************");
        return returnVal;
    }
}
