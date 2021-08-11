package com.icoolle.model.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.time.LocalDateTime;


/**
* @author: YY
* @description: 商品表管理保存实体参数
* @projectName: ProductSaveDTO
* @package: com.icoolle.model.pms.dto
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
@Data
@ApiModel("商品表管理保存实体参数")
@Accessors(chain = true)
public class ProductSaveDTO implements Serializable {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

}
