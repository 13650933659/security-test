package com.cjr.securitytest.web.security.config;

import com.cjr.securitytest.web.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.cjr.securitytest.web.security.config.AbstractChannelSecurityConfig;
import com.cjr.securitytest.web.security.properties.SecurityConstants;
import com.cjr.securitytest.web.security.properties.SecurityProperties;
import com.cjr.securitytest.web.security.validatecode.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *  security 配置
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class) // 让 SecurityProperties 属性配置类型生效
public class SecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        http
                .apply(validateCodeSecurityConfig)					// 验证码的过滤器配置（image和sms）
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)		// 手机短信登录验证的配置
                .and()
			.authorizeRequests()	// 资源
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,				// 用户名密码登录请求处理url
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,           // 手机验证码登录请求处理url
                        securityProperties.getLoginPage(),	// 默认登录页面
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*"
                ).permitAll()	// 不需要认证的请求(处理所有需要验证的控制器、登录页面等等)
                .anyRequest()			// 任何请求
                .authenticated()		// 都需要认证
                .and()		// 禁用跨域请求
                .csrf().disable();
        ;
    }
}
