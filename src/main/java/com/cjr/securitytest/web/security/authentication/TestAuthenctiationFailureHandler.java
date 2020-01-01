package com.cjr.securitytest.web.security.authentication;

import com.cjr.securitytest.web.dto.response.BaseResponse;
import com.cjr.securitytest.web.security.properties.LoginResponseType;
import com.cjr.securitytest.web.security.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败后的处理器
 */
@Component
public class TestAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;


	/**
	 * 认证失败回调方法
	 * @param request
	 * @param response
	 * @param exception		认证失败的异常
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
		logger.info("登录失败");
		if (LoginResponseType.JSON.equals(securityProperties.getLoginType())) {		// 登录类型如果是发送的ajax请求，则返回json（例如：app请求，或者浏览器异步的登录请求）
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(BaseResponse.createFailResult(exception.getMessage())));
		}else{
			super.onAuthenticationFailure(request, response, exception);
		}
	}

}
