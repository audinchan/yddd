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
	 * ��������ļ�����ʽ.
	 * @param password ��������.
	 * @return ���ܺ������.
	 */
	public String encodePassword(String password);
}
