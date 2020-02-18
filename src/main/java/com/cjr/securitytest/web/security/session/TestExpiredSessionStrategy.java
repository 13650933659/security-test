package com.cjr.securitytest.web.security.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * session并发导致失效的处理策略
 */
public class TestExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	public TestExpiredSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	/**
	 *
	 * @param event
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}

	/**
	 * session并发导致失效=true
	 * @return
	 */
	@Override
	protected boolean isConcurrency() {
		return true;
	}

}
