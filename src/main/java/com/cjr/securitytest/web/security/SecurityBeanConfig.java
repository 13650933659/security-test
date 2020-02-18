package com.cjr.securitytest.web.security;

import com.cjr.securitytest.web.security.properties.SecurityProperties;
import com.cjr.securitytest.web.security.session.TestExpiredSessionStrategy;
import com.cjr.securitytest.web.security.session.TestInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * security 的bean配置
 */
@Configuration
public class SecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * session失效默认的处理策略
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new TestInvalidSessionStrategy(securityProperties.getSession().getSessionInvalidUrl());
	}


	/**
	 * session并发导致失效的处理策略
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new TestExpiredSessionStrategy(securityProperties.getSession().getSessionInvalidUrl());
	}

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
