/**
 * 
 */
package com.yodoo.rent.extservice.impl;

import org.acegisecurity.providers.encoding.PasswordEncoder;

import com.yodoo.rent.extservice.IPasswordEncoder;

/**
 * 使用ACEGI进行密码加密.
 * @author audin
 *
 */
public class PasswordEncoderAcegiImpl implements IPasswordEncoder {
	private PasswordEncoder passwordEncoder;

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public String encodePassword(String password) {
		return passwordEncoder.encodePassword(password, null);
	}
	
}
