package com.icoolle.component.filter;

import com.alibaba.fastjson.JSON;
import com.icoolle.common.constant.enums.CommonResponseEnum;
import com.icoolle.common.core.model.Result;
import com.icoolle.tool.JwtTokenProviderTool;
import com.icoolle.tool.SysContentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.icoolle.common.constant.consts.JwtTokenConst.*;


public class MyJwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(MyJwtTokenAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token请求头信息
        String authHeader = request.getHeader(TOKEN_HEADER);
        logger.info("authHeader:"+authHeader);
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
//            RedisServiceTool redisServiceTool = (RedisServiceTool) SpringContextUtil.getBean("redisServiceTool");
//            String userId= JwtTokenProviderTool.parseJWTGetId(authHeader);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken("666", null, new ArrayList<>())
            );
        }

        //去空格处理
        TrimReqParamWrapper trimReqParamWrapper = new TrimReqParamWrapper(request);
        SysContentUtil.setRequest(request);
        SysContentUtil.setResponse(response);
        filterChain.doFilter(trimReqParamWrapper, response);


    }

    /**
     * 返回用户未登录
     * @param httpResponse
     * @throws IOException
     */
    public void setHttpResponse(HttpServletResponse httpResponse) throws IOException{
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_OK);

        //解决跨域的问题
        httpResponse.setHeader("Access-Control-Allow-Origin","*");
        httpResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With,X-App-Id, X-Token");
        httpResponse.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");

        Result result = new Result(CommonResponseEnum.LOGIN_ERROR,CommonResponseEnum.LOGIN_ERROR.getMessage());
        httpResponse.getWriter().write(JSON.toJSONString(result));
    }
}
