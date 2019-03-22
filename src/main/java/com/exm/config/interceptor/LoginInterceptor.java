package com.exm.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.exm.common.R;
import com.exm.common.REnum;
import com.exm.common.RedisKey;
import com.exm.common.UserSession;
import com.exm.config.header.MyHeader;
import com.exm.business.user.User;
import com.exm.business.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("doc") || requestURI.contains("webjars") || requestURI.contains("swagger")){
            return true;
        }
        String token = request.getHeader(MyHeader.MY_TOKEN);
        if (StringUtils.isNotEmpty(token)){
            String phone = stringRedisTemplate.opsForValue().get(RedisKey.LOGIN_TOKEN_KEY_USER + token);
            log.info("LoginInterceptor phone is:{}",phone);
            if (StringUtils.isNotEmpty(phone)){
                String rToken = stringRedisTemplate.opsForValue().get(RedisKey.LOGIN_USER_KEY_TOKEN + phone);
                log.info("LoginInterceptor redis token is:{}",rToken);
                if (StringUtils.isNotEmpty(rToken) && rToken.equals(token)){
                    log.info("token验证成功");
                    User user = userService.lambdaQuery().eq(User::getPhone, phone).one();
                    UserSession.setUserId(user.getId());
                    return true;
                }else {
                    R r1 = R.failure(REnum.Token_Error);
                    response.setHeader("Content-Type","application/json;charset=UTF-8");
                    response.getWriter().write(JSONObject.toJSONString(r1));
                    return false;
                }
            }
        }
        R r = R.failure(REnum.Token_Not_Exist);
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        String s = JSONObject.toJSONString(r);
        response.getWriter().write(s);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserSession.remove();
    }
}
