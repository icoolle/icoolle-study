package com.icoolle.config;

import cn.hutool.core.util.ObjectUtil;
import com.icoolle.annotation.IgnoredUrl;
import com.icoolle.common.constant.consts.IgnoredUrlConst;
import com.icoolle.component.filter.MyJwtTokenAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Map;

/**
 *  Security 核心配置类 开启控制权限至Controller
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/userlogin").permitAll()// 对登录要允许匿名访问
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                //.loginPage(LOGIN_PAGE)
                //.loginProcessingUrl(LOGIN_PROCESSING_URL)
                .and()
                // 自定义token过滤校验处理器
                .addFilterBefore(new MyJwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .headers().cacheControl();
    }

    /**
     * 页面资源或是文件夹下的资源过滤
     *
     * @param webSecurity web权限配置
     */
    @Override
    public void configure(WebSecurity webSecurity) {
        List<String> urls = IgnoredUrlConst.IGNOREDURL;
        if (ObjectUtil.isNotEmpty(urls)) {
            urls.forEach(url -> webSecurity.ignoring().antMatchers(url));
        }
        Map<String, Object> beans = super.getApplicationContext().getBeansWithAnnotation(IgnoredUrl.class);
        for (Object bean : beans.values()) {
            Class<?> aClass = bean.getClass();
            IgnoredUrl ignoredUrl = aClass.getAnnotation(IgnoredUrl.class);
            if (null != ignoredUrl && 0 != ignoredUrl.urls().length) {
                for (String url : ignoredUrl.urls()) {
                    webSecurity.ignoring().antMatchers(url);
                }
            }
        }
    }
}
