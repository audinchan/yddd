package com.yodoo.rent.extservice.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.yodoo.rent.extservice.IAddressLookupManager;
import com.yodoo.rent.extservice.LTPoint;
import com.yodoo.rent.model.City;
import com.yodoo.rent.service.ICityManager;

public class AddressLookupManagerImpl extends JdbcDaoSupport implements
		IAddressLookupManager {
	
	private ICityManager cityManager;

	public void setCityManager(ICityManager cityManager) {
		this.cityManager = cityManager;
	}

	public String getAddress(String ipAddress) {
		long ip = ipToLong(ipAddress);
		return (String) getJdbcTemplate()
				.queryForObject(
						"select country from iptable where start_ip <= ? and end_ip >= ?",
						new Object[] { ip, ip }, String.class);
	}

	public LTPoint getLatLng(String address) {
		/*
		 * 1、按省市县区分词，确定数组0123分别代表省市县区。
		 * 2、根据省市县区查找符合的结果集，按3210匹配，如果找到，则返回。
		 * 3、根据如果包含大学、学院，则取出，根据学校直接定位坐标（学校坐标库），返回。
		 * 4、如果学校坐标库中没有此学校，则根据学校查找所在区、县、市、省，返回。
		 * 5、如果以上都未成功，则根据前三个字和前两个字查找匹配省市，如果找到，则
		 * 5.1、根据省市后面的三个字和两个字查找匹配城市，如果找到则返回。
		 * 5.2、如果未找到，则返回省市。
		 */
		
		// TODO 增加Cache处理，以提高效率。
		List<City> list = cityManager.findAll();
		for (City city : list) {
			if (address.indexOf(city.getName()) != -1) {
				return new LTPoint(city.getName(), address, city.getLat(), city.getLng());
			}
		}
		return null;
	}

	/**
	 * 将127.0.0.1 形式的IP地址转换成10进制整数，这里没有进行任何错误处理
	 * 
	 * @param strIP
	 * @return
	 */
	public static long ipToLong(String strIP) {
		long[] ip = new long[4];
		int position1 = strIP.indexOf(".");
		int position2 = strIP.indexOf(".", position1 + 1);
		int position3 = strIP.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIP.substring(0, position1));
		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIP.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
}
