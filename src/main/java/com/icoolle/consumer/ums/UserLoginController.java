package com.icoolle.consumer.ums;


import com.icoolle.annotation.RestMappingController;
import com.icoolle.common.constant.consts.AuthorConst;
import com.icoolle.common.constant.consts.MethodConst;
import com.icoolle.common.core.model.Result;
import com.icoolle.model.ums.dto.UserLoginDTO;
import com.icoolle.model.ums.po.AuthUserEntity;
import com.icoolle.provider.ums.service.AuthUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 登录
 */
@Slf4j
@RestMappingController
@Api(tags = "登录管理")
public class UserLoginController {

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @ApiOperation(value = "用户登录接口", httpMethod = MethodConst.POST)
    @PostMapping("userlogin")
    public Result userLogin(@RequestBody @Valid UserLoginDTO userLoginDTO, HttpServletRequest request){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(userLoginDTO.getPassword());
        log.info(encode);
        AuthUserEntity authUserEntity = authUserDetailsService.userLogin(userLoginDTO);
        return Result.success(authUserEntity);
    }

}
