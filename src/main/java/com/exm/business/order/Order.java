package com.exm.business.order;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-12 15:16
 */
@Data
@TableName("t_order")
public class Order extends Model<Order>{

    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private Long userId;
    private Double amount;
    private Integer delFlag;

    @TableField(value = "create_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
