/**
 * 
 */
package com.yodoo.rent.extservice;

import com.yodoo.rent.model.User;

/**
 * @author audin
 *
 */
public interface IAfterUserRegister {
	public void afterRegisterUser(User user, User operator);
}
