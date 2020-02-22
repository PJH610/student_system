package com.letopo.auth.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-20 11:03
 */
public class JWTToken implements AuthenticationToken {

    private String jwtToken;// 根据用户名和密码换取到的 token

    public JWTToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return jwtToken;
    }


    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }
}
