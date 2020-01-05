package com.cjr.securitytest.web.security.properties;

import com.cjr.securitytest.web.security.validatecode.ValidateCode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全框架用到的一些常量
 */
@ConfigurationProperties(prefix = "test.security")
@Data
public class SecurityProperties {

	/**
	 * 登录响应的类型
	 */
	private LoginResponseType loginType = LoginResponseType.JSON;

	/**
	 * 默认的登录页面
	 */
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

	/**
	 * 验证码属性配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();

}

