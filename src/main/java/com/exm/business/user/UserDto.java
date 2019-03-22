package com.exm.business.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户参数")
public class UserDto {

    @ApiModelProperty("用户姓名")
    @NotEmpty(message = "用户姓名不能为空")
    private String userName;

    @ApiModelProperty("年龄")
    @NotNull(message = "年龄不能为空")
    @DecimalMin(value = "0",message = "年龄不能小于0")
    @DecimalMax(value = "150",message = "年龄不能大于150")
    private Integer age;
}
