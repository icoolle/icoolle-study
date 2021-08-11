package com.icoolle.component.handler;

import com.icoolle.component.exception.BaseException;
import com.icoolle.model.ums.po.AuthUserEntity;
import com.icoolle.provider.ums.service.AuthUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.icoolle.common.constant.enums.CommonResponseEnum.ADMIN_USER_LIMIT;
import static com.icoolle.common.constant.enums.CommonResponseEnum.PASSWORD_ERROR;


/**
 * 自定义登录逻辑验证处理器
 */
@Slf4j
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private AuthUserDetailsService authUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = authentication.getPrincipal().toString();
        // 获取表单中输入的密码
        String password = authentication.getCredentials().toString();

        // 查询用户是否存在
        AuthUserEntity authUserEntity = authUserDetailsService.loadUserByUsername(userName);
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, authUserEntity.getPassword())) {
            throw new BaseException(PASSWORD_ERROR.getCode(),PASSWORD_ERROR.getMessage());
        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        ADMIN_USER_LIMIT.assertIsFalse(authUserEntity.getDisplay());



        return new UsernamePasswordAuthenticationToken(authUserEntity, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }


}
