package com.letopo.common.controller;

import com.letopo.common.exception.BizException;
import com.letopo.common.model.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 增强/通知
 * 对后端控制器进行异常处理
 * @author jh
 * @version 1.0
 * @date 2020-02-18 10:41
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    //private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * 认证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public R handlerAuthenticationException(AuthenticationException e) {
        log.error("认证失败", e);
        e.printStackTrace();
        return R.error(401, "请先认证通过后再访问系统...", e.getMessage());
    }

    /**
     * 授权检查异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public R handlerAuthorizationException(AuthorizationException e) {
        log.error("您没有操作该功能的权限", e);
        e.printStackTrace();
        return R.error(403, "您没有操作该功能的权限", e.getMessage());
    }

    /**
     * 自定义业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    public R handlerBizException(BizException e) {
        log.debug("操作失败，请联系系统管理员", e.getMessage());
        e.printStackTrace();
        return R.error(e.getCode(), "操作失败，请联系系统管理员", e.getMessage());
    }

    /**
     * 处理其它未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public R handlerException(Throwable e) {
        log.debug("服务器内部错误", e.getMessage());
        e.printStackTrace();
        return R.error(500, "服务器内部错误", e.getMessage());
    }
}
