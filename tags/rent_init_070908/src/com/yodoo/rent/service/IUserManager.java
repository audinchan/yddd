package com.yodoo.rent.service;
// Generated 2007-8-6 20:26:59 by Hibernate Tools 3.2.0.b9 with mintgen

import com.yodoo.rent.model.User;


public interface IUserManager extends IBaseManager<User, String> {
	/**
	 * 注册用户.
	 * @param user 用户信息.
	 * @param operator 操作者.
	 */
	public void registerUser(User user, User operator);
}
