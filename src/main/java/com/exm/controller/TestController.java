package com.exm.controller;

import com.exm.business.order.OrderDto;
import com.exm.business.order.OrderService;
import com.exm.business.user.User;
import com.exm.business.user.UserDto;
import com.exm.business.user.UserService;
import com.exm.common.R;
import com.exm.common.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@Api(value = "测试接口",tags = "订单")
public class TestController {

    @GetMapping("/")
    @ApiOperation("测试接口")
    public String index(){
        return "index";
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/submit/order/")
    @ApiOperation("提交订单")
    public R submitOrder(@RequestBody @Valid OrderDto orderDto){
        return orderService.insert(orderDto);
    }

    @PostMapping("/submit/user")
    @ApiOperation("提交用户信息")
    public R submitUser(@RequestBody @Valid UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        boolean b = userService.save(user);
        if (b){
            return R.success();
        }
        return R.fail(REnum.Operate_Fail);
    }

}
