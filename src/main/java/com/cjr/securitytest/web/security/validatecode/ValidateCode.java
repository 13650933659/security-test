package com.cjr.securitytest.web.security.validatecode;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * 验证码
 */
@Data
public class ValidateCode {

	/**
	 * 验证码
	 */
	private String code;

	/**
	 * 失效时间
	 */
	private LocalDateTime expireTime;

	/**
	 * 构造方法
	 * @param code			验证码
	 * @param expireIn		失效时间（单位秒）
	 */
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	/**
	 * 构造方法
	 * @param code			验证码
	 * @param expireTime		失效时间
	 */
	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}

	/**
	 * 判断此验证码是否失效
	 * @return
	 */
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	
}
