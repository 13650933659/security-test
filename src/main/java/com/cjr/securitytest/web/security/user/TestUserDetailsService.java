package com.cjr.securitytest.web.security.user;

import com.cjr.securitytest.web.security.dal.entity.User;
import com.cjr.securitytest.web.security.dal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *  自定义用户验证逻辑
 */
@Component
@Slf4j
public class TestUserDetailsService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserService userService;

	/**
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("表单登录用户名:" + username);
		User user = userService.getByUsername(username);
		if (user == null) {
			user = userService.getByPhone(username);		// 根据电话获取用户以后考虑做的更加严谨一些（支持短信验证码登录的功能）
			if (user == null) {
				throw new UsernameNotFoundException(String.format("user %s non existent", username));
			}
		}
//		String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword()); 	 // 这个密码是我们从数据库查出来，现在这里就直接模拟一下，以后添加用户是必须把密码加密存入数据库
		// 后面四个boolean自己写逻辑，有一个为false则就校验失败（我在这里就全部写true因为我没有这个业务）
		return new User(username, user.getPassword(),
					true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
