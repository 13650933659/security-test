package com.cjr.securitytest.web.security.validatecode;

import com.cjr.securitytest.web.security.validatecode.sms.DefaultSmsCodeSender;
import com.cjr.securitytest.web.security.validatecode.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码的bean配置
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
