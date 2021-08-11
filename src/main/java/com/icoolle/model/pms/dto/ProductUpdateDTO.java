package com.icoolle.model.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
* @author: YY
* @description: 商品表修改实体参数
* @projectName: ProductUpdateDTO
* @package: com.icoolle.model.pms.dto
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
@Data
@ApiModel("商品表修改实体参数")
@Accessors(chain = true)
public class ProductUpdateDTO extends ProductSaveDTO {

    @NotNull(message = "主键ID不能为空值")
    @ApiModelProperty("主键ID")
    private Long id;
}
