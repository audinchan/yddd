/**
 * 
 */
package com.yodoo.rent.extservice;

import java.util.List;

import com.yodoo.rent.model.City;

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
	
	/**
	 * 根据地址解析坐标.
	 * @param address
	 * @return
	 */
	public LTPoint getLatLng(String address);
	
	/**
	 * 从IP地址库中获取随机的地址信息，用于测试。
	 * @return
	 */
	public String getRandomAddress();
	
	/**
	 * 查找最近的城市.
	 * 
	 * @param count
	 * @param lat
	 * @param lng
	 * @return
	 */
	public List<City> findNearCitys(int count, float lat, float lng);
}
