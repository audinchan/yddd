/**
 * 
 */
package com.yodoo.rent.webapp.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author audin
 *
 */
public class SessionListenerInvoker implements HttpSessionListener {
	private static final String CONFIG_NAME = "sessionListenserTarget";
	
	private HttpSessionListener getInvoker(HttpSessionEvent ev) {
		String target = ev.getSession().getServletContext().getInitParameter(CONFIG_NAME);
		if (target == null || target.trim().length() == 0) {
			target = "defaultSessionListenser";
		} else {
			target = target.trim();
		}
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(ev.getSession().getServletContext());
		HttpSessionListener bean = (HttpSessionListener) ctx.getBean(target);
		return bean;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent ev) {
		getInvoker(ev).sessionCreated(ev);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent ev) {
		getInvoker(ev).sessionDestroyed(ev);
	}

}
