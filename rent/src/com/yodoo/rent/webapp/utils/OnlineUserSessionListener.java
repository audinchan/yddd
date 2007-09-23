/**
 * 
 */
package com.yodoo.rent.webapp.utils;

import java.util.Random;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.yodoo.rent.commons.Constant;
import com.yodoo.rent.model.OnlineUser;
import com.yodoo.rent.service.IOnlineUserManager;

/**
 * 用户访问网站时自动在online_user表中添加一条记录.
 * 
 * @author audin
 *
 */
public class OnlineUserSessionListener implements HttpSessionListener {
	private IOnlineUserManager onlineUserManager;
	private static int count = 0;

	public void setOnlineUserManager(IOnlineUserManager onlineUserManager) {
		this.onlineUserManager = onlineUserManager;
	}

	public static int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent ev) {
		count ++;
		OnlineUser ou = new OnlineUser();
		ou.setNickname("user_" + count);
		onlineUserManager.save(ou);
		ev.getSession().setAttribute(Constant.ONLINE_USER, ou);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent ev) {
		OnlineUser ou = (OnlineUser) ev.getSession().getAttribute(Constant.ONLINE_USER);
		if (ou != null) {
			onlineUserManager.removeById(ou.getId());
			count --;
		}
	}

}
