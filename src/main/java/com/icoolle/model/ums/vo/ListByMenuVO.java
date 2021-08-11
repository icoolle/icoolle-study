package com.icoolle.model.ums.vo;


import com.icoolle.common.core.model.BaseTreeBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单列表返回实体类
 */
@Data
@ApiModel("菜单列表返回实体类")
public class ListByMenuVO extends BaseTreeBean {

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单排序")
    private Integer menuSort;

    @ApiModelProperty(value = "资源地址")
    private String href;

    @ApiModelProperty(value = "资源图标")
    private String icon;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidFlag;

    @ApiModelProperty(value = "权限值")
    private String permission;

    @ApiModelProperty(value = "菜单类型（1：菜单  2：按钮）")
    private Integer menuType;

}
