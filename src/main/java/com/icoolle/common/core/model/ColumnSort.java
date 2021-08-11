package com.icoolle.common.core.model;

import com.icoolle.common.constant.enums.SortEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 排序实体
 */
@Data
@Accessors(chain = true)
@ApiModel("排序实体")
public class ColumnSort {

    @ApiModelProperty("排序字段")
    private String field;

    @ApiModelProperty("排序")
    private SortEnum order;
}
