package com.yodoo.rent.service;
// Generated 2007-8-11 18:08:53 by Hibernate Tools 3.2.0.b9 with mintgen

import com.yodoo.rent.model.Role;


public interface IRoleManager extends IBaseManager<Role, String> {
	/**
	 * Ĭ�Ͻ�ɫ��.
	 * @return
	 */
	public String getDefaultRoleName();
	
	/**
	 * ��ȡĬ�Ͻ�ɫ.
	 * @return
	 */
	public Role getDefaultRole();
}
