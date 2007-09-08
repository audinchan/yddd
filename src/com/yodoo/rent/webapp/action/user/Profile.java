/**
 * 
 */
package com.yodoo.rent.webapp.action.user;

import org.nestframework.annotation.DefaultAction;

import com.yodoo.rent.commons.NotImplementedException;

/**
 * 管理用户Profile.
 * 
 * @author audin
 *
 */
public class Profile {
	/**
	 * 显示我的Profile.
	 * @return
	 */
	@DefaultAction
	public Object show() {
		throw new NotImplementedException();
	}
	
	/**
	 * 编辑我的Profile.
	 * @return
	 */
	public Object edit() {
		throw new NotImplementedException();
	}
	
	/**
	 * 保存我的Profile.
	 * @return
	 */
	public Object save() {
		throw new NotImplementedException();
	}
}
