package com.yodoo.rent.webapp.action.reg;

import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.localization.ActionMessages;
import org.nestframework.validation.Param;
import org.nestframework.validation.Validate;
import org.nestframework.validation.Validations;

import com.yodoo.rent.commons.UserExistsException;
import com.yodoo.rent.model.User;
import com.yodoo.rent.service.IUserManager;
import com.yodoo.rent.webapp.action.BaseAction;

@Validate
public class Register extends BaseAction {
	
	@DefaultAction
	public Object show() {
		return "/reg/show.jsp";
	}
	
	public Object register(ActionMessages errors, HttpSession s) {
		try {
			userManager.registerUser(user, getLoginUser(s));
		} catch (UserExistsException e) {
			errors.add("user.username", "errors.register.user_exists");
			return "/reg/show.jsp";
		}
		return "/reg/result.jsp";
	}
	
	//////////// services ////////////
	@Spring
	private IUserManager userManager;
	
	//////////// properties ///////////
	@Validations({
		@Validate(type="required", fieldName="user.username", label="user.username", on="register"),
		@Validate(type="mask", fieldName="user.username", label="user.username", on="register", msg="errors.register.username_invalid", params= {
				@Param(name="mask", value="^[a-z0-9-_.]+@?[a-z0-9-_.]+$")
		}),
		@Validate(type="required", fieldName="user.password", label="user.password", on="register")
	})
	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
