package com.cjr.securitytest.web.security.validatecode.sms;

/**
 * 短信验证码发送器（发送方法不同自己实现）
 */
public interface SmsCodeSender {

	/**
	 * 发送验证吗的方法
	 * @param mobile		手机
	 * @param code			验证码
	 */
	void send(String mobile, String code);

}
