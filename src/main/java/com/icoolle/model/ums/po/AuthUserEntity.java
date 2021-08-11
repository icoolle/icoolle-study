package com.icoolle.model.ums.po;

import com.icoolle.model.ums.vo.ListByMenuVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 登录权限用户
 */
@Data
@ApiModel("登录权限用户")
@Accessors(chain = true)
public class AuthUserEntity extends AdminUser implements UserDetails {

    @ApiModelProperty(value = "菜单权限资源信息")
    private List<ListByMenuVO> permissionMenuListVOList;

    @ApiModelProperty(value = "按钮权限资源信息")
    private List<ListByMenuVO> permissionButtonListVOList;

    /**
     * @return 返回权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getUserName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
