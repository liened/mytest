package com.exm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-14 17:15
 */
@Data
@ApiModel("登录参数")
public class LoginDto {

    @ApiModelProperty("手机号")
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String pwd;

}
