package com.cjr.securitytest.web.security.config;

import com.cjr.securitytest.web.security.authentication.TestAuthenticationFailureHandler;
import com.cjr.securitytest.web.security.authentication.TestAuthenticationSuccessHandler;
import com.cjr.securitytest.web.security.properties.SecurityConstants;
import com.cjr.securitytest.web.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 各种渠道的安全配置（浏览器、App）
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected TestAuthenticationSuccessHandler testAuthenticationSuccessHandler;
	
	@Autowired
	protected TestAuthenticationFailureHandler testAuthenctiationFailureHandler;

	@Autowired
	protected SecurityProperties securityProperties;

	/**
	 * 表单登录认证（用户名密码的那一种）
	 * @param http
	 * @throws Exception
	 */
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)						// 当请求需要身份认证时，默认跳转的url
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)		// 表单登录认证提交处理的url（这个默认实现的 没有拦截）
			.successHandler(testAuthenticationSuccessHandler)								// 表单登录成功的处理器
			.failureHandler(testAuthenctiationFailureHandler);								// 表单登录失败的处理器
	}
	
}
