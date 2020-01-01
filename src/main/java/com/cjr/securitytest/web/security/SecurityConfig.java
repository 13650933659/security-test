package com.cjr.securitytest.web.security;

import com.cjr.securitytest.web.security.config.AbstractChannelSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends AbstractChannelSecurityConfig {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .loginPage("/authentication/require")		 // 自定义登录页面（我们使用一个控制器吧，比较灵活，可以把html和那种数据请求的分开处理）
//                .loginProcessingUrl("/authentication/form")	 // 定义登录的表单提交时请求的url，默认是 /login
//                .successHandler(imoocAuthenticationSuccessHandler)								// 表单登录成功的处理器
//                .failureHandler(imoocAuthenticationFailureHandler);								// 表单登录失败的处理器
//			.authorizeRequests()	// 资源
//                .antMatchers(
//                        "/authentication/require",				// 需要登录的处理请求
//                        securityProperties.getBrowser().getLoginPage()	// 默认登录页面
//                ).permitAll()	// 不需要认证的请求(处理所有需要验证的控制器、登录页面等等)
//                .anyRequest()			// 任何请求
//                .authenticated()		// 都需要认证
//                .and()		// 禁用跨域请求
//                .csrf().disable();
        ;
    }
}
