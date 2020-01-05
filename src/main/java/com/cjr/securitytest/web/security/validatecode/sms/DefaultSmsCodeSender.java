package com.cjr.securitytest.web.security.validatecode.sms;


/**
 * 短信验证码的发送器（应该要借助短信运营商发送短信给用户）
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }

}
