/**
 * 
 */
package com.yodoo.rent.extservice;

/**
 * @author audin
 *
 */
public interface IPasswordEncoder {
	
	/**
	 * 返回密码的加密形式.
	 * @param password 明文密码.
	 * @return 加密后的密码.
	 */
	public String encodePassword(String password);
}
