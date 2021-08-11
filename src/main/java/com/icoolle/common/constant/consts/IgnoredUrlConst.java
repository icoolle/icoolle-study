package com.icoolle.common.constant.consts;

import java.util.Arrays;
import java.util.List;


public interface IgnoredUrlConst {

    List<String> IGNOREDURL = Arrays.asList(
            "/favicon.ico",
            "/webjars/springfox-swagger-ui/**",
            "/swagger-resources/**",
            "/v3/**",
            "/v2/**",
            "swagger-ui.html",
            "/**/swagger-ui.html",
            "/doc.html",
            "/error",
            "/static/**",
            "/druid/**",
            // TODO： 静态资源
            "/webapp/**",
            "/webjars/**",
            "/swagger/**",
            "/**/*.css",
            "/**/*.js",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.ttf",
            "/*.html",
            "/userlogin",
            "/login");
}
