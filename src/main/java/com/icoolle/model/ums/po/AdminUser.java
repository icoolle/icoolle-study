package com.icoolle.model.ums.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icoolle.common.core.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 用户实体类
 */
@Data
@ApiModel("用户实体类")
@Accessors(chain = true)
@TableName("ums_admin_user")
public class AdminUser extends BaseBean {

    @ApiModelProperty(value = "用户code")
    private String userCode;

    @ApiModelProperty(value = "用户")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "真实名称")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "上次登录ip")
    private String lastLoginIp;

    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "是否禁用")
    private Boolean display;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否记住token")
    private Boolean rememberFlag;

    @ApiModelProperty(value = "是否多个用户登录")
    private Boolean multipleFlag;

    @ApiModelProperty("职位")
    private String jobs;

    @ApiModelProperty("工号")
    private String jobNo;

    @ApiModelProperty("分机号")
    private String extensionNo;

    @ApiModelProperty("办工地点")
    private String workSite;

    @ApiModelProperty("入职时间")
    private LocalDateTime hiredate;

}
