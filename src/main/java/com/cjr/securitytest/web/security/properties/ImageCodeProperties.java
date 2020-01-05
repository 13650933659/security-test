package com.cjr.securitytest.web.security.properties;

import lombok.Data;

/**
 * 图片验证码的属性配置
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
	
	public ImageCodeProperties() {
		setLength(4);
	}

	/**
	 * 验证码图片宽度
	 */
	private int width = 67;

	/**
	 * 验证码图片长度
	 */
	private int height = 23;

}
