package com.icoolle.provider.ums.service;

import com.icoolle.model.ums.dto.UserLoginDTO;
import com.icoolle.model.ums.po.AuthUserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface AuthUserDetailsService extends UserDetailsService {

    @Override
    AuthUserEntity loadUserByUsername(String userName) throws UsernameNotFoundException;

    AuthUserEntity userLogin(UserLoginDTO userLoginDTO);

}
