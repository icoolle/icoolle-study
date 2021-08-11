package com.icoolle.common.constant.consts;

import java.util.concurrent.TimeUnit;


public interface JwtTokenConst {

    /**
     * token头名字
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * token前缀
     */
    String TOKEN_PREFIX = "Bearer_";

    /**
     * 主体信息
     */
    String SUBJECT = "icoolle";

    /**
     * 过期时间
     */
    long EXPIRITION = TimeUnit.DAYS.toMillis(1);


    /**
     * 秘钥
     */
    String APPSECRET_KEY = "icoolle-_SECRET";


    /**
     * 保存在token中个用户信息key
     */
    String CURRENT_USER_INFO_KEY = "CURRENT_USER_INFO_KEY";
}
