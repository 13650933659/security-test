package com.cjr.securitytest.web.security.properties;

import lombok.Data;

/**
 * 短信验证码的属性配置
 */
@Data
public class SmsCodeProperties {
	/**
	 * 验证码长度
	 */
	private int length = 4;

	/**
	 * 验证码有效时间（单位秒）
	 */
	private int expireIn = 60;

	/**
	 * 需要验证的url
	 */
	private String url;

}
