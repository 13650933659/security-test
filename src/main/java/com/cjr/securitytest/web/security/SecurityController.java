package com.cjr.securitytest.web.security;

import com.cjr.securitytest.web.dto.response.BaseResponse;
import com.cjr.securitytest.web.security.properties.SecurityConstants;
import com.cjr.securitytest.web.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 自定义所有需要验证的处理器
 * @author: Chenjiaru
 * @create: 2020-01-04 13:02
 */
@RestController
@Slf4j
public class SecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();	// 请求缓存（springsecurity 跳转到这个 /authentication/require 会先把先前的http请求放入缓存）
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;


    /**
     * 当需要身份认证时，跳转到这里
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED) // 401
    public BaseResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
//            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {	// 如果是pc端的请求，直接跳转到登录页面
            if (1==1) {	// 如果是pc端的请求，直接跳转到登录页面
                redirectStrategy.sendRedirect(request, response, securityProperties.getLoginPage());
            }
        }
        return BaseResponse.createFailResult("访问的服务需要身份认证，请引导用户到登录页");  // 如果是api的请求，提示那边先去登录认证
    }

}
