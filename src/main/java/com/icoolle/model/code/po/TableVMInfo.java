package com.icoolle.model.code.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 实体表模板信息
 */
@Data
@ApiModel("实体表模板信息")
@Accessors(chain = true)
public class TableVMInfo implements Serializable {

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "实体备注")
    private String comment;

    @ApiModelProperty(value = "实体类名")
    private String className;

    @ApiModelProperty(value = "实体表类名")
    private String tableName;

    @ApiModelProperty(value = "请求路径名")
    private String pathName;

    @ApiModelProperty(value = "模块名")
    private String moduleName;

    @ApiModelProperty(value = "基础包名")
    private String packageName;

    @ApiModelProperty(value = "实体类字段类型集合")
    private String javaTypeList;

    @ApiModelProperty(value = "当前时间")
    private String nowDate;

    @ApiModelProperty(value = "是否需要继承")
    private boolean flagExtends;

    @ApiModelProperty(value = "是否覆盖文件")
    private Boolean flag;

    @ApiModelProperty(value = "实体类字段信息集合")
    private List<TableColumnInfo> tableColumnInfoList;

    @ApiModelProperty(value = "实体类字段信息集合")
    private List<TableColumnInfo> tableColumnInfoListTwo;
}
