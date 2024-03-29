package com.yodoo.rent.webapp.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.ui.logout.LogoutHandler;

public class SessionlogoutHandlerImpl implements LogoutHandler {

	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			//session.removeAttribute(Constant.USER_KEY);
		}
	}

}
