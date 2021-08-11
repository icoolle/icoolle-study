package com.icoolle.model.code.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 接受页面生成代码实体
 */
@Data
@ApiModel("接受页面生成代码实体")
@Accessors(chain = true)
public class TableDesignSaveDTO implements Serializable {

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "模块名")
    private String moduleName;

    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "注释")
    private String comment;

    @ApiModelProperty(value = "是否覆盖文件")
    private Boolean flag = false;

    @ApiModelProperty(value = "实体类字段信息")
    private List<TableInfoDTO> tableInfoDTOList;
}
