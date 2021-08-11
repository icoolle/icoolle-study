package com.icoolle.model.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author: YY
* @description: 商品表信息列表返回实体
* @projectName: ProductListVO
* @package: com.icoolle.model.pms.vo
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
@Data
@ApiModel("商品表信息列表返回实体")
@Accessors(chain = true)
public class ProductListVO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("创建人ID")
    private Long createUserId;

    @ApiModelProperty("主键ID")
    private LocalDateTime createTime;

    @ApiModelProperty("修改人ID")
    private Long modifyUserId;

    @ApiModelProperty("修改时间")
    private LocalDateTime modifyTime;

    @ApiModelProperty("删除标记")
    private Integer delFlag;

    @ApiModelProperty("备注")
    private String remark;

}
