package com.mysearch.Config;

import com.mysearch.Entity.User;
import com.mysearch.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String user_email = token.getUsername();
        User user = userMapper.getUserByEmail(user_email);
        if (user == null) {
            return null;
        }
        // 设置盐加密
        ByteSource salt = ByteSource.Util.bytes(user.getUser_email());

        return new SimpleAuthenticationInfo(token.getPrincipal(), user.getUser_password(), salt, "UserRealm");
    }
}
