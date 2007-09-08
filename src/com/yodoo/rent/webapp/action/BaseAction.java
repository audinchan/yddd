/**
 * 
 */
package com.yodoo.rent.webapp.action;

import javax.servlet.http.HttpSession;

import com.yodoo.rent.commons.Constant;
import com.yodoo.rent.model.User;

/**
 * @author audin
 *
 */
public abstract class BaseAction implements Constant {
	/**
	 * 当前页码.
	 */
	protected int pageNo = 1;
	
	/**
	 * 分页大小.
	 */
	protected int pageSize = 10;
	
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
}
