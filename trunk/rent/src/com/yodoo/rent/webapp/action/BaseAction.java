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
	 * ��ǰҳ��.
	 */
	protected int pageNo = 1;
	
	/**
	 * ��ҳ��С.
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
	 * ��Session�л�ȡ��¼�û�����.
	 * 
	 * @param s HttpSession.
	 * @return User����.
	 */
	public static User getLoginUser(HttpSession s) {
		return (User) s.getAttribute(USER_KEY);
	}
}
