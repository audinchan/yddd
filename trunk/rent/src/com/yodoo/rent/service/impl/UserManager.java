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
	 * ��ɫManager.
	 */
	private IRoleManager roleManager;
	
	/**
	 * ������ܽӿ�.
	 */
	private IPasswordEncoder passwordEncoder;
	
	/**
	 * Ĭ���Ƿ񼤻��û�.
	 */
	private boolean defaultEnabled = true;
	
	/**
	 * ע���û��ĺ���¼�������.
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
	 * ע���û�.
	 * @param user ע���û���Ϣ.
	 * @param operator ������.
	 */
	public void registerUser(User user, User operator) {
		if (null != get(user.getUsername())) {
			throw new UserExistsException();
		}
		
		// ʹ��ϵͳ������ܹ��߽����������
		user.setPassword(passwordEncoder.encodePassword(user.getPassword()));
		
		// ����Ĭ�Ͻ�ɫ
		user.getRoles().add(roleManager.getDefaultRole());
		
		user.setEnabled(defaultEnabled);
		
		if (StringUtil.isEmpty(user.getDisplayName())) {
			user.setDisplayName(user.getUsername());
		}
		
		save(user);
		
		// �����û�ע��ɹ����¼���
		if (afterUserRegister != null) {
			afterUserRegister.afterRegisterUser(user, operator);
		}
	}
}
