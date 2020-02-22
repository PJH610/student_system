package com.letopo.auth.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.UUID;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-20 11:06
 */
public class JWTUtil {
    // 私钥
    private static final String SECRETKEY = "letopo";

    /**
     * 生成一个jwtToken
     *
     * @param subject 用户名
     * @param expire  有效时间
     * @return json web token
     */
    public static String generatorToken(String subject, Long expire) {
        JwtBuilder jwt = Jwts.builder();
        // 设置token的唯一标识
        jwt.setId(UUID.randomUUID().toString());
        // 设置token的主体
        jwt.setSubject(subject);
        // 签发者
        jwt.setIssuer("letopo");
        // 签发时间
        jwt.setIssuedAt(new Date());
        // 设置有效时间
        if (null != expire) {
            Date expiration = new Date(System.currentTimeMillis() + expire);
            jwt.setExpiration(expiration);
        }
        // 私钥
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRETKEY);
        // 签名
        jwt.signWith(SignatureAlgorithm.HS256, secretKeyBytes);
        return jwt.compact();
    }
}
