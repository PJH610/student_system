package com.letopo.auth.controller;

import com.letopo.auth.util.JWTUtil;
import com.letopo.common.model.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-20 11:01
 */
@RestController
public class AuthController {

    /**
     * 认证未通过
     *
     * @return
     */
    @GetMapping("/unauth")
    public R unauthorized() {
        return R.error(401, "请先认证通过后再访问系统...");
    }

    /**
     * 用户名密码换取 token
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("api/login")
    public R login(String username, String password) {
        //1.用户名密码认证已经通过 admin
        //2.生成jwtToken
        String token = JWTUtil.generatorToken(username, 1000 * 5L * 60 * 30L);
        return R.ok("获取token成功...", token);
    }
}
