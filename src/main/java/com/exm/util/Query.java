package com.exm.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ApiModel("分页参数")
public class Query<T> implements Serializable{

    @ApiModelProperty("当前页")
    private int currentPage =1;
    @ApiModelProperty("每页条数")
    private int pageSize = 10;
    @ApiModelProperty("参数")
    private T param;


}
