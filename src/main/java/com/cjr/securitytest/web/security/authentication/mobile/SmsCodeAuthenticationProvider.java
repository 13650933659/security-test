package com.cjr.securitytest.web.security.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 手机短信登录认证的 认证处理器 （相当于 AbstractUserDetailsAuthenticationProvider）
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	/**
	 * 获取用户信息的接口（这里最好使用传进来的，比较灵活，因为手机短信登录认证的方法和表单登录认证有所区别）
	 */
	private UserDetailsService userDetailsService;

	/**
	 * 认证的处理器逻辑（认证成功则返回认证成功的 SmsCodeAuthenticationToken 否则抛异常 ）
	 * @param authentication
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

		// 这里需要通过用户手机查询用户（能查到用户就算认证成功，因为前面已经经过了短信校验了）
		UserDetails user = userDetailsService.loadUserByUsername(((String) authenticationToken.getPrincipal()));
		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());		// 这个 details 可以不用
		return authenticationResult;
	}

	/**
	 * 此方法返回 true 则此处理器生效（即： authentication= SmsCodeAuthenticationToken 生效）
	 * @param authentication
	 * @return
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
