package com.cjr.securitytest.web.security;

import com.cjr.securitytest.web.security.validatecode.sms.DefaultSmsCodeSender;
import com.cjr.securitytest.web.security.validatecode.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * security 的bean配置
 */
@Configuration
public class SecurityBeanConfig {

	/**
	 * 加解密工具(就算同一个文本，生成结果都不一样，但是否相同他自己知道的)
	 * PasswordEncoder的一个实现类
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
