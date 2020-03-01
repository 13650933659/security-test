package com.cjr.securitytest.web.security.validatecode;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 验证码（需要序列号，因为他存入session可能到时session会存到redis）
 */
@Data
public class ValidateCode implements Serializable {

	private static final long serialVersionUID = 1L;

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
