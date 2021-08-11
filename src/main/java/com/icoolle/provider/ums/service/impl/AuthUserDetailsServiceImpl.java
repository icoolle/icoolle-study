package com.icoolle.provider.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icoolle.annotation.TransactionalService;
import com.icoolle.component.handler.UserAuthenticationProvider;
import com.icoolle.model.ums.dto.UserLoginDTO;
import com.icoolle.model.ums.po.AdminUser;
import com.icoolle.model.ums.po.AuthUserEntity;
import com.icoolle.model.ums.vo.ListByMenuVO;
import com.icoolle.provider.ums.mapper.AdminUserMapper;
import com.icoolle.provider.ums.service.AuthUserDetailsService;
import com.icoolle.tool.JwtTokenProviderTool;
import com.icoolle.tool.RedisServiceTool;
import com.icoolle.tool.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.icoolle.common.constant.enums.CommonResponseEnum.ADMIN_USER_NOT_EXIST;


@Slf4j
@TransactionalService
public class AuthUserDetailsServiceImpl implements AuthUserDetailsService {

    @Resource
    AdminUserMapper adminUserMapper;

    @Resource
    UserAuthenticationProvider userAuthenticationProvider;



    @Resource
    private RedisServiceTool redisServiceTool;

    @Override
    public AuthUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserEntity authUserEntity = new AuthUserEntity();
        AdminUser adminUser = adminUserMapper.selectOne(
                new QueryWrapper<AdminUser>()
                        .eq("user_name", username)
                        .eq("DEL_FLAG", false)
                        .last("LIMIT 1"));
        ADMIN_USER_NOT_EXIST.assertNotNull(adminUser);
        BeanUtil.copyProperties(adminUser, authUserEntity);
        return authUserEntity;
    }


    @Override
    public AuthUserEntity userLogin(UserLoginDTO userLoginDTO) {
        // 该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
        final Authentication authentication = userAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUserName(), userLoginDTO.getPassword()));
        // 获取返回的实体类
        final AuthUserEntity authUserEntity = (AuthUserEntity) authentication.getPrincipal();
        // id
        final Long id = authUserEntity.getId();

        // 按钮权限和菜单权限
//        List<ListByMenuVO> list =new ArrayList<>();
//        Map<Integer, List<ListByMenuVO>> listMap = list.stream().collect(Collectors.groupingBy(ListByMenuVO::getMenuType));
//        authUserEntity.setPermissionButtonListVOList(listMap.get(2));
//        authUserEntity.setPermissionMenuListVOList(TreeListUtil.disposeTreeList(listMap.get(1), 0L));

        // 生成token
        String token;
        String userToken = authUserEntity.getToken();
        try {
            if (StringUtil.isEmpty(userToken) || !JwtTokenProviderTool.validateToken(userToken, authUserEntity.getUserName())) {
                token = JwtTokenProviderTool.createJWT(authUserEntity);
            } else {
                token = userToken;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            token = JwtTokenProviderTool.createJWT(authUserEntity);
        }

        authUserEntity.setToken(token);

        // 跟新用户信息
        AdminUser adminUser = new AdminUser();
        adminUser.setLastLoginIp(userLoginDTO.getLoginIp())
                .setLastLoginTime(LocalDateTime.now())
                .setToken(token)
                .setModifyUserId(id)
                .setId(id);
        adminUserMapper.updateById(adminUser);

        // 把用户信息放到缓存里面
        redisServiceTool.set("user:"+id, authUserEntity);
        return authUserEntity;
    }

}
