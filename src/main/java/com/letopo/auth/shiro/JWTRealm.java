package com.letopo.auth.shiro;

import com.letopo.auth.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.xml.bind.DatatypeConverter;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-20 11:03
 */
public class JWTRealm extends AuthorizingRealm {

    private static final String SECRETKEY = "letopo";

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 根据用户名和密码换取到的 token
        JWTToken jwtToken = (JWTToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();
        Claims claims;
        User user = new User();
        // 对 token 解析，对 token 合法性进行校验
        claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY))
                .parseClaimsJws(token)
                .getBody();
        user.setId(claims.getId());
        user.setUsername(claims.getSubject());
        if (claims.get("roles") != null) user.setRoles((String) claims.get("roles"));
        if (claims.get("permissions") != null) user.setPermissions((String) claims.get("permissions"));
        System.out.println("认证完成....");
        return new SimpleAuthenticationInfo(user, Boolean.TRUE, getName());
    }

    /**
     * 授权检查
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 简单授权信息对象，对象中包含用户的角色和权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");

        System.out.println("授权完成....");
        return info;
    }

    @Override
    public Class<?> getAuthenticationTokenClass() {
        // 配置该Realm只支持JWTToken
        return JWTToken.class;
    }
}

