package com.letopo.auth.config;

import com.letopo.auth.shiro.JWTFilter;
import com.letopo.auth.shiro.JWTRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-20 11:01
 */
@Configuration
public class AuthConfig {

    /**
     * 配置安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 注入JWTRealm
        securityManager.setRealm(new JWTRealm());
        return securityManager;
    }

    /**
     * 配置权限过滤器
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 注入安全管理器
        bean.setSecurityManager(securityManager());
        // 如果用户未登录，跳转到未认证接口
        bean.setLoginUrl("/login");
        // 设置自定义的过滤器
        Map<String, Filter> filters = new LinkedHashMap();
        // jwtFilter 是过滤器的名称
        filters.put("jwtFilter", new JWTFilter());
        bean.setFilters(filters);

        // 配置过滤规则
        Map<String, String> chain = new LinkedHashMap<>();
        chain.put("/api/login", "anon");

        chain.put("/api/auth/**", "noSessionCreation,jwtFilter");

        bean.setFilterChainDefinitionMap(chain);
        return bean;
    }

    /**
     * 启用Shiro注解
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        // 注入安全管理器
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    /**
     * 启用Shiro内部Bean生命周期管理
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启aop对shiro的bean的动态代理
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        return creator;
    }
}

