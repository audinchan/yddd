/**
 * 
 */
package com.yodoo.rent.webapp.action.user;

import org.nestframework.annotation.DefaultAction;

import com.yodoo.rent.commons.NotImplementedException;

/**
 * �����û�Profile.
 * 
 * @author audin
 *
 */
public class Profile {
	/**
	 * ��ʾ�ҵ�Profile.
	 * @return
	 */
	@DefaultAction
	public Object show() {
		throw new NotImplementedException();
	}
	
	/**
	 * �༭�ҵ�Profile.
	 * @return
	 */
	public Object edit() {
		throw new NotImplementedException();
	}
	
	/**
	 * �����ҵ�Profile.
	 * @return
	 */
	public Object save() {
		throw new NotImplementedException();
	}
}
