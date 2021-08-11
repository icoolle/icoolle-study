package com.icoolle.model.ums.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户登录参数
 */
@Data
@ApiModel("用户登录参数")
@Accessors(chain = true)
public class UserLoginDTO implements Serializable {

    @ApiModelProperty(value = "用户",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "登录ip", hidden = true)
    private String loginIp;

}
