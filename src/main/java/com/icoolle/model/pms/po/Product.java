package com.icoolle.model.pms.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icoolle.common.core.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;



/**
* @author: YY
* @description: 商品表管理实体类
* @projectName: Product
* @package: com.icoolle.model.pms.po
* @date: 2021-08-11 16:03:53
* @version: V1.0.0
*/
@Data
@ApiModel("商品表管理实体类")
@TableName("pms_product")
@Accessors(chain = true)
public class Product  extends BaseBean { 
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

}
