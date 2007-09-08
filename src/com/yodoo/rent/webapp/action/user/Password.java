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
 * ��������.
 * 
 * @author audin
 *
 */
public class Password extends BaseAction {
	
	/**
	 * ��ʾ��������ҳ��.
	 * 
	 * @return
	 */
	@DefaultAction
	public Object show() {
		return DEFAULT_PAGE;
	}
	
	/**
	 * �޸�����.
	 * @param s HttpSession.
	 * @return
	 */
	public Object changePassword(HttpSession s, ActionMessages errors) {
		if (StringUtil.isNotSame(password, password2)) {
			errors.add("password2", "������������벻һ�£�");
			return DEFAULT_PAGE;
		}
		
		User user = userManager.get(getLoginUser(s).getUsername());
		user.setPassword(EncodeUtil.md5(password));
		userManager.save(user);
		
		return "!/user/Password.a";
	}
	
	/////////////// ҳ�� //////////
	
	public static final String DEFAULT_PAGE = "/user/change_password.jsp";
	
	/////////////// ҵ���� /////////
	
	@Spring
	private IUserManager userManager;
	
	/////////////// ���� ///////////
	
	/**
	 * �ύ��������.
	 */
	private String password;
	
	/**
	 * ������ȷ��.
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
