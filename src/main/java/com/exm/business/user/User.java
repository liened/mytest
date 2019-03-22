package com.exm.business.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_sys_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;


    private String userName;


    private Integer age;

    //删除标志 0-否 1-是
    private Integer delFlag = 0;

    @TableField(value = "create_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

    //修改时间
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}
