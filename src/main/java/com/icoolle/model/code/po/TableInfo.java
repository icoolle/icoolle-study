package com.icoolle.model.code.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 * 表信息
 */
@Data
@ApiModel("表信息数据实体")
@Accessors(chain = true)
public class TableInfo implements Serializable {

    @ApiModelProperty(value = "列名")
    private String columnName;

    @ApiModelProperty(value = "列名类型")
    private String dataType;

    @ApiModelProperty(value = "列名备注")
    private String columnComment;
}
