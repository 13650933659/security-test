package com.cjr.securitytest.web.security.properties;

import lombok.Data;

/**
 * 验证码属性
 */
@Data
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();
	
	private SmsCodeProperties sms = new SmsCodeProperties();

}
