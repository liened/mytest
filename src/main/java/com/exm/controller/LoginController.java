package com.exm.controller;

import com.exm.common.R;
import com.exm.module.dto.LoginDto;
import com.exm.module.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(value = "登录接口",tags = "登录")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    private R login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
}
