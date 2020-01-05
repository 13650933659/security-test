/**
 * 
 */
package com.cjr.securitytest.web.security.validatecode.sms;

import com.cjr.securitytest.web.security.properties.SecurityProperties;
import com.cjr.securitytest.web.security.validatecode.ValidateCode;
import com.cjr.securitytest.web.security.validatecode.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 手机短信验证码生成器
 */
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}

}
