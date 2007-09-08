/**
 * 
 */
package com.yodoo.rent.webapp.ajax;

import javax.servlet.http.HttpSession;

import com.yodoo.rent.model.User;
import com.yodoo.rent.service.IUserManager;
import com.yodoo.rent.webapp.action.BaseAction;

/**
 * @author audin
 *
 */
public class UserUtil {
	private IUserManager userManager;
	
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public boolean checkUserExists(String username) {
		return null != userManager.get(username);
	}
	
	public void register(User user, HttpSession s) {
		userManager.registerUser(user, BaseAction.getLoginUser(s));
	}
}
