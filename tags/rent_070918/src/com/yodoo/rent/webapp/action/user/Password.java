/**
 * 
 */
package com.yodoo.rent.webapp.action.user;

import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.EncodeUtil;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.localization.ActionMessages;

import com.yodoo.rent.model.User;
import com.yodoo.rent.service.IUserManager;
import com.yodoo.rent.webapp.action.BaseAction;

/**
 * 设置密码.
 * 
 * @author audin
 *
 */
public class Password extends BaseAction {
	/**
	 * 显示密码设置页面.
	 * 
	 * @return
	 */
	@DefaultAction
	public Object show() {
		return DEFAULT_PAGE;
	}
	
	/**
	 * 修改密码.
	 * @param s HttpSession.
	 * @return
	 */
	public Object changePassword(HttpSession s, ActionMessages errors) {
		if (StringUtil.isNotSame(password, password2)) {
			errors.add("password2", "两次输入的密码不一致！");
			return DEFAULT_PAGE;
		}
		
		User user = userManager.get(getLoginUser(s).getUsername());
		user.setPassword(EncodeUtil.md5(password));
		userManager.save(user);
		
		return "!/user/Password.a";
	}
	
	/////////////// 页面 //////////
	
	public static final String DEFAULT_PAGE = "/user/change_password.jsp";
	
	/////////////// 业务类 /////////
	
	@Spring
	private IUserManager userManager;
	
	/////////////// 属性 ///////////
	
	/**
	 * 提交的新密码.
	 */
	private String password;
	
	/**
	 * 新密码确认.
	 */
	private String password2;
	
	/////////////// getter and setter //////////

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
