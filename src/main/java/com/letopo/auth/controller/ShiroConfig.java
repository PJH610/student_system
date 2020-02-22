package com.letopo.auth.controller;

import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.context.annotation.Bean;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-20 11:02
 */
// @Configuration
public class ShiroConfig {


    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }


}
