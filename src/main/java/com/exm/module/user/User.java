package com.exm.module.user;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-11 15:53
 * @description
 */
@Data
@ApiModel("用户信息")
@TableName("t_sys_user")
public class User extends Model<User> {

    @ApiModelProperty("用户的id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("用户姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("删除标志 0-否 1-是")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
