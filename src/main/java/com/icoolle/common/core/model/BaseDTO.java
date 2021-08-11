package com.icoolle.common.core.model;


import com.icoolle.common.constant.enums.SysCommonEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 基础查询父类
 */
@Data
@Accessors(chain = true)
@ApiModel("")
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO extends PageParam {

    @ApiModelProperty(value = "主键搜索，匹配前端下拉列表单数据")
    private Long primaryId;

    @ApiModelProperty(value = "关键词搜索，匹配前端下拉列表单数据")
    private String primaryName;

    @ApiModelProperty(value = "关键词搜索，匹配前端下拉列表单数据")
    private Long primaryOrganId;

    @ApiModelProperty(value = "关键词查询:一般用于单个或多个字段模糊匹配")
    private String keywords;

    @ApiModelProperty(value = "导出业务名称==》由后端提供具体业务名称")
    private String exportKeyName;

    @ApiModelProperty(value = "归属公司")
    private Long companyId;

    @ApiModelProperty(value = "机构id")
    private Long orgId;

    @ApiModelProperty(value = "筛选是否值字段")
    private Boolean disPlay;

    @ApiModelProperty(value = "开始创建时间")
    private LocalDateTime startCreateTime;

    @ApiModelProperty(value = "结束创建时间")
    private LocalDateTime endCreateTime;

    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    @ApiModelProperty(value = "创建人名称搜索")
    private String createUserName;

    @ApiModelProperty("机构编码")
    private String orgCode;

    @ApiModelProperty("下拉时的机构编码")
    private String organCode;

    @ApiModelProperty("通用状态筛选")
    private SysCommonEnum commonStatus = null;
}
