package com.cjr.securitytest.web.security.authentication;

import com.cjr.securitytest.web.security.properties.LoginResponseType;
import com.cjr.securitytest.web.security.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录验证成功进入处理(根据用户的配置返回json数据还是直接跳转页面)
 */
@Component
public class TestAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 验证成功进入处理
	 * @param request
	 * @param response
	 * @param authentication		认证成功的对象
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
		logger.info("登录认证成功");
		if (LoginResponseType.JSON.equals(securityProperties.getLoginType())) {  // 登录类型如果是发送的ajax请求，则返回json（例如：app请求，或者浏览器异步的登录请求）
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {		// 默认的处理室调整到刚刚请求的那个url
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
