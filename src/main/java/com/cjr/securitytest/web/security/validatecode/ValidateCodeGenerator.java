package com.cjr.securitytest.web.security.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成验证码
	 * @param request	{@link ServletWebRequest}
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);
	
}
