/**
 * 
 */
package com.yodoo.rent.webapp.utils;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yodoo.rent.commons.Constant;
import com.yodoo.rent.model.OnlineUser;
import com.yodoo.rent.service.IOnlineUserManager;

/**
 * @author audin
 * 
 */
@SuppressWarnings("serial")
public class OnlineUserCheckServlet extends HttpServlet {
	public static final String ONLINEUSER_MANAGER_BEAN = "onlineUserManager";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		dispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		dispatch(req, resp);
	}

	protected void dispatch(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		OnlineUser ou = (OnlineUser) req.getSession().getAttribute(Constant.ONLINE_USER);
		if (ou != null && StringUtil.isEmpty(ou.getIpaddr())) {
			IOnlineUserManager mgr = getMgr(req.getSession().getServletContext());
			OnlineUser u = mgr.get(ou.getId());
			u.setIpaddr(req.getRemoteAddr());
			mgr.save(u);
		}
	}
	
	private IOnlineUserManager getMgr(ServletContext context) {
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		return (IOnlineUserManager) ctx.getBean(ONLINEUSER_MANAGER_BEAN);
	}
}
