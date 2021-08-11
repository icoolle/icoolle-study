package com.icoolle.common.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE;
import static com.icoolle.common.constant.consts.BeanTableFieldConst.*;


/**
 * 基础po类
 */
@Data
@Accessors(chain = true)
@ApiModel("po实体基类")
public class BaseBean implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("主键ID")
    private Long id;

    @TableField(value = CREATEDBY, fill = INSERT)
    @ApiModelProperty("创建人")
    private Long createUserId;

    @TableField(value = CREATEDTIME, fill = INSERT)
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @TableField(value = UPDATEDBY, fill = INSERT_UPDATE)
    @ApiModelProperty("修改人")
    private Long modifyUserId;

    @TableField(value = UPDATEDTIME, fill = INSERT_UPDATE)
    @ApiModelProperty("修改时间")
    private LocalDateTime modifyTime;

    @TableLogic
    @TableField(value = DELFLAG, fill = INSERT)
    @ApiModelProperty("删除标记 0-否，1-是")
    private Boolean delFlag;
}

