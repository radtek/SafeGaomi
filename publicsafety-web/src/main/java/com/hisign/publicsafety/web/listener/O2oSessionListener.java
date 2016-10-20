/**
 * 
 */
package com.hisign.publicsafety.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chailiangzhi
 * @date 2015-9-16
 * 
 */
public class O2oSessionListener implements HttpSessionListener {

	private static final Logger logger = LoggerFactory.getLogger(O2oSessionListener.class);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String sessionId = se.getSession().getId();
		logger.info("sessionCreated,sessionId={}", sessionId);

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String sessionId = se.getSession().getId();
		logger.info("sessionDestroyed,sessionId={}", sessionId);
	}

}
