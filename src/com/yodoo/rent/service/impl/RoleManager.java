package com.yodoo.rent.service.impl;
// Generated 2007-8-11 18:08:54 by Hibernate Tools 3.2.0.b9 with mintgen


import com.yodoo.rent.model.Role;
import com.yodoo.rent.service.IRoleManager;

public class RoleManager extends BaseManager<Role, String> implements IRoleManager {
	
	/**
	 * 默认角色名.
	 */
	private String defaultRoleName = "NORMALUSER";
	
	private Role defaultRole = null;
	
	public void setDefaultRoleName(String defaultRoleName) {
		this.defaultRoleName = defaultRoleName;
	}
	
	public String getDefaultRoleName() {
		return defaultRoleName;
	}

	/**
	 * 获取默认角色.
	 * @return
	 */
	public Role getDefaultRole() {
		if (defaultRole == null) {
			defaultRole = get(defaultRoleName);
		}
		return defaultRole;
	}
}
