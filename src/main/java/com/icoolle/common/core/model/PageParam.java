package com.icoolle.common.core.model;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


/**
 * 分页参数
 */
@ApiModel(value = "分页参数")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageParam implements Serializable {

    @ApiModelProperty(value = "页码，从 1 开始", required = true, example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页条数，最大值为 1000", required = true, example = "10")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    @ApiModelProperty(value = "排序字段,以及排序方式")
    private List<ColumnSort> sortBy = Lists.newArrayList();

    @ApiModelProperty(value = "开始行数")
    private int offset;

    public int getOffset() {
        return null == pageNum ? 0 : (pageNum - 1) * pageSize;
    }


}
