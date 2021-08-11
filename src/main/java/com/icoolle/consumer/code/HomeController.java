package com.icoolle.consumer.code;

import com.icoolle.annotation.IgnoredUrl;
import com.icoolle.annotation.MappingController;
import com.icoolle.common.constant.consts.MethodConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面相关接口管理
 */
@IgnoredUrl(urls = {"/*", "/login", "/home"})
@Api(tags = "页面相关接口管理")
@MappingController
public class HomeController {

    
    @ApiOperation(value = "初始页",  httpMethod = MethodConst.GET)
    @GetMapping("/")
    public String index() {
        return "login";
    }

  
    @ApiOperation(value = "登录页",  httpMethod = MethodConst.GET)
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @ApiOperation(value = "系统首页",  httpMethod = MethodConst.GET)
    @GetMapping("/home")
    public String home() {
        return "index";
    }

}
