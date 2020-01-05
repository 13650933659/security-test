package com.cjr.securitytest.web.security.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 手机短信登录认证的Token（相当于 UsernamePasswordAuthenticationToken）
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	/**
	 * 认证前是手机号码，认证之后放的 {@link org.springframework.security.core.userdetails.UserDetails} 用户的信息，包括拥有的权限
	 */
	private final Object principal;

	// ~ Constructors
	// ===================================================================================================

	/**
	 * 创建未认证的构造方法
	 * @param mobile	手机号码
	 */
	public SmsCodeAuthenticationToken(String mobile) {
		super(null);
		this.principal = mobile;
		setAuthenticated(false);	// 表示未认证
	}

	/**
	 * 创建已认证的构造方法
	 * @param principal	用户的认证信息
	 * @param authorities	拥有的权限
	 */
	public SmsCodeAuthenticationToken(Object principal,
                                      Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		super.setAuthenticated(true); // must use super, as we override（表示已认证）
	}

	// ~ 方法
	// ===================================================================================================
	/**
	 * 获取密码的方法，这个在我们这里没有了，返回null即可
	 * @return
	 */
	@Override
	public Object getCredentials() {
		return null;
	}

	/**
	 * 获取用户认证后的信息
	 * @return
	 */
	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	/**
	 * 重写父类的标记是否已经认证的方法（这里只接受 false ）
	 * @param isAuthenticated
	 * @throws IllegalArgumentException
	 */
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}
		super.setAuthenticated(false);
	}

	/**
	 * 这个目前没用到直接调用父类的即可
	 */
	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}

}
