package com.exm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2019-01-10 17:20
 * @description
 */
@Data
@ApiModel("用户学生信息")
public class UserStudentDto {

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为 Null")
    private Integer userId;

    @ApiModelProperty("用户姓名")
    @NotEmpty(message = "用户姓名不能为空")
    private String userName;

    @ApiModelProperty("学生ID")
    @NotNull(message = "学生ID不能为 Null")
    private Integer studentId;

    @ApiModelProperty("学生分数")
    @NotNull(message = "学生分数不能为 Null")
    private Integer score;
}
