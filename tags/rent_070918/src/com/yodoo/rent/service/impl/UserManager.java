package com.yodoo.rent.service.impl;
// Generated 2007-8-6 20:27:00 by Hibernate Tools 3.2.0.b9 with mintgen


import org.nestframework.commons.utils.StringUtil;

import com.yodoo.rent.commons.UserExistsException;
import com.yodoo.rent.extservice.IAfterUserRegister;
import com.yodoo.rent.extservice.IPasswordEncoder;
import com.yodoo.rent.model.User;
import com.yodoo.rent.service.IRoleManager;
import com.yodoo.rent.service.IUserManager;

public class UserManager extends BaseManager<User, String> implements IUserManager {
	/**
	 * 角色Manager.
	 */
	private IRoleManager roleManager;
	
	/**
	 * 密码加密接口.
	 */
	private IPasswordEncoder passwordEncoder;
	
	/**
	 * 默认是否激活用户.
	 */
	private boolean defaultEnabled = true;
	
	/**
	 * 注册用户的后继事件处理器.
	 */
	private IAfterUserRegister afterUserRegister;
	
	public void setRoleManager(IRoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public void setPasswordEncoder(IPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setDefaultEnabled(boolean defaultEnabled) {
		this.defaultEnabled = defaultEnabled;
	}
	

	public void setAfterUserRegister(IAfterUserRegister afterUserRegister) {
		this.afterUserRegister = afterUserRegister;
	}
	

	/**
	 * 注册用户.
	 * @param user 注册用户信息.
	 * @param operator 操作者.
	 */
	public void registerUser(User user, User operator) {
		if (null != get(user.getUsername())) {
			throw new UserExistsException();
		}
		
		// 使用系统密码加密工具进行密码加密
		user.setPassword(passwordEncoder.encodePassword(user.getPassword()));
		
		// 分配默认角色
		user.getRoles().add(roleManager.getDefaultRole());
		
		user.setEnabled(defaultEnabled);
		
		if (StringUtil.isEmpty(user.getDisplayName())) {
			user.setDisplayName(user.getUsername());
		}
		
		save(user);
		
		// 处理用户注册成功的事件。
		if (afterUserRegister != null) {
			afterUserRegister.afterRegisterUser(user, operator);
		}
	}
}
