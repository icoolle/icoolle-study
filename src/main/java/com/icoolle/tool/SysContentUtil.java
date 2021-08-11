package com.icoolle.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SysContentUtil {

    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<>();

    public static HttpServletRequest getRequest() {
        return requestLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletResponse getResponse() {
        return responseLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseLocal.set(response);
    }

}
