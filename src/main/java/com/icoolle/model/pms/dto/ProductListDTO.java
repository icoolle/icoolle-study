package com.icoolle.model.pms.dto;

import com.icoolle.common.core.model.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author: YY
* @description: 商品表信息列表查询参数实体类
* @projectName: ProductListDTO
* @package: com.icoolle.model.pms.dto
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
@Data
@ApiModel("商品表信息列表查询参数实体类")
@Accessors(chain = true)
public class ProductListDTO extends BaseDTO {

}
