/**
 * 
 */
package com.yodoo.rent.extservice;

/**
 * @author audin
 *
 */
public interface IAddressLookupManager {
	/**
	 * 根据IP地址解析实际地址.
	 * @param ipAddress IP地址.
	 * @return
	 */
	public String getAddress(String ipAddress);
}
