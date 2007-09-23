/**
 * 
 */
package com.yodoo.rent.webapp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author audin
 *
 */
public class DefaultSessionListenser implements HttpSessionListener {
	private List<HttpSessionListener> listeners = new ArrayList<HttpSessionListener>();

	public void setListeners(List<HttpSessionListener> listeners) {
		this.listeners = listeners;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent ev) {
		for (HttpSessionListener l: listeners) {
			l.sessionCreated(ev);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent ev) {
		for (HttpSessionListener l: listeners) {
			l.sessionDestroyed(ev);
		}
	}

}
