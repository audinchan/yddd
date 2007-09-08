package com.yodoo.rent.service;
// Generated 2007-8-11 18:08:53 by Hibernate Tools 3.2.0.b9 with mintgen

import com.yodoo.rent.model.Role;


public interface IRoleManager extends IBaseManager<Role, String> {
	/**
	 * 默认角色名.
	 * @return
	 */
	public String getDefaultRoleName();
	
	/**
	 * 获取默认角色.
	 * @return
	 */
	public Role getDefaultRole();
}
