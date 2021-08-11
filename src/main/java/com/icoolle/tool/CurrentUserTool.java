package com.icoolle.tool;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 当前登录用户信息工具
 */
@Slf4j
@Component
public class CurrentUserTool {

    @Resource
    RedisServiceTool redisServiceTool;

    @Resource
    HttpServletRequest request;



}
