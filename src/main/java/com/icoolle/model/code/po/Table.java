package com.icoolle.model.code.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 *  表设计
 */
@Data
@ApiModel("表格实体")
@Accessors(chain = true)
public class Table implements Serializable {

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "表设计类型")
    private String engine;

    @ApiModelProperty(value = "注释")
    private String tableComment;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
