package com.cjr.securitytest.web.security.authentication.mobile;

import com.cjr.securitytest.web.security.authentication.TestAuthenticationFailureHandler;
import com.cjr.securitytest.web.security.authentication.TestAuthenticationSuccessHandler;
import com.cjr.securitytest.web.security.user.TestUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 手机短信登录验证的配置
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	@Autowired
	private TestAuthenticationSuccessHandler testAuthenticationSuccessHandler;
	
	@Autowired
	private TestAuthenticationFailureHandler testAuthenticationFailureHandler;

	/**
	 * 获取用户信息的接口（如果是 手机短信登录验证 需要这个接口需要另外处理了，因为前台没有输入密码了）
	 */
	@Autowired
	private TestUserDetailsService testUserDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 配置 手机短信登录认证的过滤器（相当于 UsernamePasswordAuthenticationFilter ）
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));	// 配置 AuthenticationManager
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(testAuthenticationSuccessHandler);			// 认证成功的处理器
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(testAuthenticationFailureHandler);			// 认证失败后的处理器
		
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();			// 手机短信登录认证的 认证处理器 （相当于 AbstractUserDetailsAuthenticationProvider）
		smsCodeAuthenticationProvider.setUserDetailsService(testUserDetailsService);	// 灵活指定获取用户信息的接口
		
		http.authenticationProvider(smsCodeAuthenticationProvider)
			.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);		// 此时 手机短信登录认证 和 表单登录认证同时生效
	}

}
