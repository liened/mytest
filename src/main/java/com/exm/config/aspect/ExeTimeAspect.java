package com.exm.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExeTimeAspect {

    //这个写不写发现都可以
//    @Pointcut("execution(public * com.exm.controller.*.*(..))")
//    public void point(){
//
//    }

    @Before("@annotation(com.exm.config.annotation.LogBefore)")
    public void before(JoinPoint joinPoint){
        StringBuffer sb = new StringBuffer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    }

    @Around("execution(public * com.exm.controller.*.*(..))")
    public Object logExeTime(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        //参数
        Object[] paramValues = pjp.getArgs();
        String[] paramNames = ((CodeSignature)pjp.getSignature()).getParameterNames();
        StringBuffer sb = new StringBuffer("【");
        for (int i=0;i<paramNames.length;i++){
            sb.append(i+":"+paramNames[i]+"="+paramValues[i]+";");
        }
        sb.append("】");
        Object retVal = pjp.proceed();
        long spend = System.currentTimeMillis()-start;
        String ambit = "=================================================";
        log.info("\n{}\n统计信息: 执行{}.{}方法共用了{}毫秒,\n参数:{},\n执行结果:{}\n{}\n",ambit,className,methodName,spend,sb.toString(),retVal,ambit);
        return retVal;
    }
}
