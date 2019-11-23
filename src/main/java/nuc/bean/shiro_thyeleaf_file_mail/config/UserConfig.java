package nuc.bean.shiro_thyeleaf_file_mail.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import nuc.bean.shiro_thyeleaf_file_mail.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
//
@Configuration
public class UserConfig {

    //shiroFilterFactoryBean(shiro的一些过滤文件)
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
//  为了更清楚： anon:无需认证就可以访问
//               authc:必须认证才能访问
//               user:必须拥有 记住我 功能才能用
//               perms:拥有对某个资源的权限才能访问
//               role:拥有某个角色权限才能访问
        Map<String, String> filterMap = new LinkedHashMap<>();
//授权，正常的情况下，没有授权会跳转到未授权的页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录请求
        bean.setLoginUrl("/tologin");
        //未授权的情况
        bean.setUnauthorizedUrl("/noauth");
        return bean;

    }

    //DafaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }


//整合shiroDialect:用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
