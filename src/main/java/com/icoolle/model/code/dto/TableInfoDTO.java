package com.icoolle.model.code.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 页面设计的实体信息参数
 */
@Data
@ApiModel("页面设计的实体信息参数")
@Accessors(chain = true)
public class TableInfoDTO implements Serializable {

    @ApiModelProperty(value = "列名")
    private String columnName;

    @ApiModelProperty(value = "列数据库类型")
    private String dataType;

    @ApiModelProperty(value = "列名备注")
    private String columnComment;

    @ApiModelProperty(value = "列Java类型")
    private String javaType;

}
