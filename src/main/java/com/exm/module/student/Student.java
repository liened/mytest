package com.exm.module.student;

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
 * @createDate 2018-12-20 11:30
 * @description
 */
@Data
@TableName("t_student")
@ApiModel("学生信息")
public class Student extends Model<Student> {

    @TableId
    @ApiModelProperty("id")
    private Integer id;

    @TableField("s_name")
    @ApiModelProperty("姓名")
    private String sName;

    @TableField("class_id")
    @ApiModelProperty("班级")
    private Integer classId;

    @TableField("score")
    @ApiModelProperty("分数")
    private Integer score;

    @ApiModelProperty("删除标志 0-否 1-是")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
