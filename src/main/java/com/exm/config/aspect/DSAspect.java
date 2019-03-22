package com.exm.config.aspect;

import com.exm.config.annotation.DS;
import com.exm.config.datasource.DSKeyHolder;
import com.exm.config.datasource.DSType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DSAspect implements Ordered {

//    @Pointcut("@annotation(com.exm.common.annotation.DS)")
//    public void dataSourcePointCut(){
//
//    }

    @Around("@annotation(com.exm.config.annotation.DS)")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        String className = methodSignature.getDeclaringTypeName();
        String methodName = method.getName();
        DS ds = method.getAnnotation(DS.class);
        if (ds == null){
            DSKeyHolder.set(DSType.Master);
            log.debug("{}.{} DS is null ,having set Master DS",className,methodName);
        }else{
            DSKeyHolder.set(ds.value());
            log.debug("{}.{} DS is not null ,having set {} DS",className,methodName,ds.value());
        }
        try {
            return point.proceed();
        }finally {
            DSKeyHolder.clean();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
