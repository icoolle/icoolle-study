package com.icoolle.tool;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class RequestHolderUtil {

    /**
     * 获取请求
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest servletRequest;
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            servletRequest = SysContentUtil.getRequest();
        } else {
            servletRequest = ((ServletRequestAttributes) attributes).getRequest();
        }
        return servletRequest;
    }
}
