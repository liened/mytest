package com.exm.business.order;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-20 14:20
 */
@Data
@ApiModel
public class OrderDto {

    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    @DecimalMin(value = "0",message = "用户id必须大于0")
    private Long userId;

    @ApiModelProperty("申请金额")
    @NotNull(message = "申请金额不能为空")
    @DecimalMin(value = "0",message = "申请金额必须大于0")
    private Double amount;

}
