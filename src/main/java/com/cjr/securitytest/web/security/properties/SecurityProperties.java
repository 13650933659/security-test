package com.cjr.securitytest.web.security.properties;

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

}

