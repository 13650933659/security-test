package com.cjr.securitytest.web.security.logout;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功后的处理器
 */
public class TestLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。（注意：不是点击退出时处理的url）
     */
    private String logoutUrl;

    /**
     * 构造方法
     * @param logoutUrl  退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。（注意：不是点击退出时处理的url）
     */
    public TestLogoutSuccessHandler(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.warn("用户退出，可以做一些日志");
        if (StringUtils.isBlank(logoutUrl)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{msg:1}");
        } else {
            response.sendRedirect(logoutUrl);
        }
    }

}
