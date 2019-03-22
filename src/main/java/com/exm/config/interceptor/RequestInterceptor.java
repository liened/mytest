package com.exm.config.interceptor;

import com.exm.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String remortIP = IpUtil.getRemortIP(request);
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        log.info("Interceptor - originIp:{},remoteHost:{},remotePort:{}",ip,remoteHost,remotePort);
        //Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("这个是请求前校验,请求路径:{},请求者ip:{}",uri,remortIP);
        return super.preHandle(request, response, handler);
    }

    /**
     * 转换参数
     * @param parameterMap
     * @return
     */
    public Map convertParam(Map<String, String[]> parameterMap){
        Map<String,String> rtnMap = new LinkedHashMap<>();
        for (Map.Entry<String,String[]> entry:parameterMap.entrySet()){
            String key = entry.getKey();
            String[] value = entry.getValue();
            rtnMap.put(key,value[0]);
        }
        return rtnMap;
    }
}
