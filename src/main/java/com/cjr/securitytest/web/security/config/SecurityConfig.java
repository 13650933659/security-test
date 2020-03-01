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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

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
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        http
                .apply(validateCodeSecurityConfig)					// 验证码的过滤器配置（image和sms）
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)		// 手机短信登录验证的配置
                .and()
                    .sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy)			// session 失效处理策略
                    .maximumSessions(securityProperties.getSession().getMaximumSessions())		// 同一个用户在系统中的最大session数（在springsecurity看来 {@link org.springframework.security.core.userdetails.UserDetails} 是唯一的来判断此用户是否已经登录了）
                    .maxSessionsPreventsLogin(securityProperties.getSession().isMaxSessionsPreventsLogin())	// 达到最大session时是否阻止新的登录请求，默认为false，一般默认就好都是踢掉前一个用户的，为false 才会调用下面的 sessionInformationExpiredStrategy，否则不会，security直接抛异常
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)		// session 并发导致失效处理策略
                .and()
                .and()
                    .logout()
                    .logoutUrl("/logout")                              // 退出的请求（默认也是 /logout）springsecurity 帮我们实现的没有拦截（他会帮我们做 1、使当前session失效 2、清除与用户先关的remember-me记录 3、清空当前的securityContext 4、重定向到登录页面）
                    .logoutSuccessHandler(logoutSuccessHandler)	    // 退出之后的处理器
                    .deleteCookies("JSESSIONID")					    // 退出之后需要清除的cookie （java存session的id）
                .and()
                    .authorizeRequests()	// 资源
                    .antMatchers(
                            SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,				// 用户名密码登录请求处理url
                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,           // 手机验证码登录请求处理url
                            securityProperties.getLoginPage(),	// 默认登录页面
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*"
                            ,securityProperties.getSession().getSessionInvalidUrl()
//                            ,"/static/*"      // 没有生效
                            ,"/index.html"
                            ,"/logout.html"
    //                        "/static/js/login.js"
                    ).permitAll()	// 不需要认证的请求(处理所有需要验证的控制器、登录页面等等)
                    .anyRequest()			// 任何请求
                    .authenticated()		// 都需要认证
                .and()		// 禁用跨域请求
                    .csrf().disable();
        ;
    }
}
