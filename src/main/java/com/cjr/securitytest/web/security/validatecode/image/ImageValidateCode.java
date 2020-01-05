package com.cjr.securitytest.web.security.validatecode.image;


import com.cjr.securitytest.web.security.validatecode.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


/**
 * 图片验证码对象
 */
@Data
public class ImageValidateCode extends ValidateCode {
	
	private BufferedImage image;

	/**
	 * 构造方法
	 * @param image		验证码图片
	 * @param code			验证码
	 * @param expireIn		失效时间（单位秒）
	 */
	public ImageValidateCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}

	/**
	 * 构造方法
	 * @param image		验证码图片
	 * @param code			验证码
	 * @param expireTime	失效时间
	 */
	public ImageValidateCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}

}
