package com.letopo.auth.shiro;

import com.alibaba.fastjson.JSON;
import com.letopo.common.model.R;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-20 11:03
 */
@Slf4j
public class JWTFilter extends AccessControlFilter {

    /**
     * 请求到来以后响应的方法
     *
     * @param servletRequest
     * @param servletResponse
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * token 认证未通过，执行下面的方法
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("token error...");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html;charset=UTF-8");
        try {
            // 1.检查请求头中是否存在 token
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String token = request.getHeader("jwt");
            // 对该token进行认证
            JWTToken jwtToken = new JWTToken(token);
            // 获取要认证的主体
            Subject subject = SecurityUtils.getSubject();
            // 发起对主体的认证
            subject.login(jwtToken);
        } catch (ExpiredJwtException e) {
            String json = JSON.toJSONString(R.error(401, "token 过期", e.getMessage()));
            response.getWriter().write(json);
            return false;
        } catch (UnsupportedJwtException e) {
            String json = JSON.toJSONString(R.error(401, "token 无效", e.getMessage()));
            response.getWriter().write(json);
            return false;
        } catch (MalformedJwtException e) {
            String json = JSON.toJSONString(R.error(401, "token 格式错误", e.getMessage()));
            response.getWriter().write(json);
        } catch (SignatureException e) {
            String json = JSON.toJSONString(R.error(401, "token 签名无效", e.getMessage()));
            response.getWriter().write(json);
            return false;
        } catch (IllegalArgumentException e) {
            String json = JSON.toJSONString(R.error(401, "token 参数异常", e.getMessage()));
            response.getWriter().write(json);
            return false;
        } catch (Exception e) {
            String json = JSON.toJSONString(R.error(401, "token 有问题,请重新登录!!!", e.getMessage()));
            response.getWriter().write(json);
            return false;
        }
        return true;
    }
}

