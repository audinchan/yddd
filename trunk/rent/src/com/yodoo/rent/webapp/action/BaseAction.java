/**
 * 
 */
package com.yodoo.rent.webapp.action;

import javax.servlet.http.HttpSession;

import com.yodoo.rent.commons.Constant;
import com.yodoo.rent.model.OnlineUser;
import com.yodoo.rent.model.User;

/**
 * @author audin
 *
 */
public abstract class BaseAction implements Constant {
	/**
	 * 主题目录.
	 * 
	 * 如 "/51ditu" 表示主题的根目录为51ditu。
	 */
	protected String theme = "/gmap_";
	/**
	 * 当前页码.
	 */
	protected int pageNo = 1;
	
	/**
	 * 分页大小.
	 */
	protected int pageSize = 10;
	
	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 从Session中获取登录用户对象.
	 * 
	 * @param s HttpSession.
	 * @return User对象.
	 */
	public static User getLoginUser(HttpSession s) {
		return (User) s.getAttribute(USER_KEY);
	}
	
	/**
	 * 从Session中获取在线用户对象.
	 * 
	 * @param s
	 * @return
	 */
	public static OnlineUser getOnlineUser(HttpSession s) {
		return (OnlineUser) s.getAttribute(ONLINE_USER);
	}
	
	protected String getPage(String page) {
		return theme + page;
	}
	
	protected String getPage(String dir, String page) {
		return dir + theme + page;
	}
}
