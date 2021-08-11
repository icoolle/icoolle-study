package com.icoolle.common.core.model;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 基础树形公共数据
 */
@Data
@ApiModel("基础树形公共数据")
@Accessors(chain = true)
public class BaseTreeBean implements Serializable {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private Long parentId;

    /**
     * 子类数据
     */
    @ApiModelProperty(value = "子类数据")
    private List<?> childrenList;

    @ApiModelProperty(value = "子类目数")
    private int childrenCount = 0;

    public List<?> getChildrenList() {
        return ObjectUtil.isEmpty(this.childrenList) ? Lists.newArrayList() : this.childrenList;
    }
}
