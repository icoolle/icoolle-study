package com.icoolle.common.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 基础查询参数实体类
 */
@Data
@Accessors(chain = true)
@ApiModel("基础查询参数实体类")
public class SearchBaseBean implements Serializable {

    @ApiModelProperty("分页页号")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页页面大小")
    private Integer pageSize = 10;
}