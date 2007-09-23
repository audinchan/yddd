/**
 * 
 */
package com.yodoo.rent.commons;

/**
 * @author audin
 *
 */
public interface Constant {
	
	/**
	 * 登录用户在Session中的Key.
	 */
	public static final String USER_KEY = "__login_user__";
	
	/**
	 * 用户概要文件在Session中的Key.
	 */
	public static final String PROFILE_KEY = "__profile__";
	
	/**
	 * 用户当前所在城市.
	 */
	public static final String CURRENT_CITY = "__current_city__";
	
	/**
	 * 在线用户标识.
	 * 包括所有匿名用户，访问网站后在online_user表中添加一条记录。
	 */
	public static final String ONLINE_USER = "__online_user__";
}
