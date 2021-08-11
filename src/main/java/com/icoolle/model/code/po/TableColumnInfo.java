package com.icoolle.model.code.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@ApiModel("实体类字段信息")
@Accessors(chain = true)
public class TableColumnInfo implements Serializable {

    @ApiModelProperty(value = "列名")
    private String columnName;

    @ApiModelProperty(value = "列名类型")
    private String dataType;

    @ApiModelProperty(value = "列名备注")
    private String comment;

    @ApiModelProperty(value = "属性名称")
    private String attrName;
}
