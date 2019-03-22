package com.exm.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public R throwable(Throwable e){
        log.error(e.getMessage(),e);
        return R.fail(REnum.System_Error);
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception e){
        if (e.getCause() != null){
            //记录原生异常
            log.error("Exception - have cause:",e.getCause().getMessage(),e.getCause());
        }else {
            //记录异常日志
            log.error("Exception - no cause:",e.getMessage(),e);
        }
        return R.fail(REnum.System_Error);
    }

    @ExceptionHandler(NullPointerException.class)
    public R nullPointException(NullPointerException e){
        log.error("空指针异常 - NullPointerException: ",e.getMessage(),e);
        return R.failWithDetail(REnum.System_Error,"空指针异常");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public R illegalException(IllegalArgumentException e){
        log.error("参数非法异常 - IllegalArgumentException: ",e);
        return R.failWithDetail(REnum.System_Error,"参数非法异常");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R argumentException(MethodArgumentNotValidException e){
        //log.error("参数校验异常:",e);
        BindingResult bindingResult = e.getBindingResult();
        return R.failWithDetail(REnum.System_Error,bindingResult.getAllErrors().get(0).getDefaultMessage());
    }
}
