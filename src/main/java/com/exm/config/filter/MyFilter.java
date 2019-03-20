package com.exm.config.filter;

import com.exm.config.header.MyHeader;
import com.exm.config.header.RequestHeaderContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
//@ConfigurationProperties(prefix = "param.exclude")
@WebFilter(urlPatterns = {"/api/*","/openApi/*"},filterName = "MyFilter")
public class MyFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest == null){
            log.error("*********************************");
            log.error("*********************************");
            log.error("*********************************");
            log.error("filter request is null");
            return;
        }
        try{
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String ip = request.getRemoteHost();
            String token = request.getHeader("X-TOKEN");
            String divice = request.getHeader("X-DIVICE");

            MyHeader myHeader = new MyHeader();
            myHeader.setToken(token);
            myHeader.setDivice(divice);
            RequestHeaderContext.setHeader(myHeader);

            MDC.put("ip",ip);
            MDC.put("token",token);
            MDC.put("divice",divice);

            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            MDC.clear();
            RequestHeaderContext.remove();
        }

    }

    @Override
    public void destroy() {

    }
}
